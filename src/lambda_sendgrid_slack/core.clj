(ns lambda-sendgrid-slack.core
  (:require [clj-slack.chat :refer [post-message]]
            [clojure.data.json :as json]
            [clojure.java.io :as io]
            [clojure.tools.logging :as log]
            [uswitch.lambada.core :refer [deflambdafn]]))

(def slack-default-api-url "https://slack.com/api")
(def slack-default-sendgrid-username "Sendgrid")
(def slack-default-channel "#general")
(def slack-default-icon-emoji ":email:")

(defn process-sg-events
  ""
  [event]
  (let [api-url (or (System/getenv "SLACK_API_URL") slack-default-api-url)
        api-token (or (System/getenv "SLACK_API_TOKEN") "")
        channel (or (System/getenv "SLACK_CHANNEL") slack-default-channel)
        sendgrid-username (or (System/getenv "SLACK_SENDGRID_USERNAME") slack-default-sendgrid-username)
        icon-emoji (or (System/getenv "SLACK_ICON_EMOJI") slack-default-icon-emoji)
        sg-events (json/read-str (:body event) :key-fn keyword)]
    (map
     #(list
       {:api-url api-url :token api-token}
       channel
       (str (:event %) " event for: " (:email %))
       {:username sendgrid-username :icon_emoji icon-emoji})
     sg-events)))

(deflambdafn lambda-sendgrid-slack.core.handler
  [in out ctx]
  (let [event (json/read (io/reader in) :key-fn keyword)
        args (process-sg-events event)
        results (map #(apply post-message %) args)
        response {:isBase64Encoded false
                  :statusCode 200
                  :body (json/write-str {:status "ok" :events_processed (count results)})}]
    (log/info "Got event" event)
    (log/info "Posting" args)
    (with-open [w (io/writer out)]
      (json/write response w))))

(defn -main
  "I don't do a whole lot."
  [& args]
  (let [event
        {:path "/path/to/resource",
         :queryStringParameters {:foo "bar"},
         :pathParameters {:proxy "path/to/resource"},
         :headers {}
         :stageVariables {:baz "qux"}
         :resource "/{proxy+}"
         :httpMethod "POST"
         :body (json/write-str [{:email "test@example.com" :event "event_name"}])}]
    (println (map #(apply post-message %) (process-sg-events event)))))
