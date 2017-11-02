(ns lambda-sendgrid-slack.core-test
  (:require [clojure.test :refer :all]
            [lambda-sendgrid-slack.core :refer :all]
            [clojure.data.json :as json]))


(def test-sg-events [{
    :email "john.doe@sendgrid.com"
    :timestamp 1337197600
    :smtp-id "<4FB4041F.6080505@sendgrid.com>"
    :sg_event_id "sendgrid_internal_event_id"
    :sg_message_id "sendgrid_internal_message_id"
    :event "processed"}
   {:email "john.doe@sendgrid.com"
    :timestamp 1337966815
    :ip "X.XX.XXX.XX"
    :sg_event_id "sendgrid_internal_event_id"
    :url "https://sendgrid.com"
    :sg_message_id "sendgrid_internal_message_id"
    :useragent "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36"
    :event "click"}
   {:sg_message_id "sendgrid_internal_message_id"
    :email "john.doe@sendgrid.com"
    :ip "X.XX.XXX.XX"
    :useragent "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36"
    :event "group_unsubscribe"
    :sg_user_id 123
    :asm_group_id 42
    :timestamp 1337969592
    :sg_event_id "sendgrid_internal_event_id"}])

(def test-api-gw-event
  {:path "/path/to/resource",
   :queryStringParameters {:foo "bar"},
   :pathParameters {:proxy "path/to/resource"},
   :headers {}
   :stageVariables {:baz "qux"}
   :resource "/{proxy+}"
   :httpMethod "POST"
   :body (json/write-str test-sg-events)})

(deftest test-process-sg-event
  (testing "FIXME, I fail."
    (let [events (process-sg-events test-api-gw-event)]
      (is (= (count events) (count test-sg-events))))))
