(defproject basic-microservice "1.0.0"
  :description "A ready to use Clojure application with a minimal configuration."
  :url "https://github.com/matheusfrantz/basic-microservice"

  :plugins [[lein-cljfmt "0.7.0"]
            [lein-kibit "0.1.8"]
            [lein-cloverage "1.2.2"]]

  :dependencies [[org.clojure/clojure "1.10.3"]
                 [com.stuartsierra/component "1.0.0"]
                 [compojure "1.6.2"]
                 [ring/ring-core "1.9.3"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-jetty-adapter "1.9.3"]
                 [ring/ring-json "0.5.1"]]

  :test-selectors {:default     (complement :integration)
                   :integration :integration
                   :all         (constantly true)}

  :aliases {"check"    ["do" ["kibit"] ["cljfmt" "check"]]
            "fix"      ["do" ["kibit" "--replace"] ["cljfmt" "fix"]]
            "coverage" ["cloverage"]}

  :main basic-microservice.server)
