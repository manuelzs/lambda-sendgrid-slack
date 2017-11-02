(defproject lambda-sendgrid-slack "0.1.2-SNAPSHOT"
  :description "AWS Lambda function to send Sendgrid events to Slack"
  :url "http://github.com/manuelzs/lambda-sendgrid-slack"
  :license {:name "Eclipse Public License"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [uswitch/lambada "0.1.2"]
                 [org.clojure/data.json "0.2.6"]
                 [org.julienxx/clj-slack "0.5.5"]
                 [org.clojure/tools.logging "0.4.0"]]

  :main ^:skip-aot lambda-sendgrid-slack.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
