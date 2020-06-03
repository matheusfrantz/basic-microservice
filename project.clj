(defproject basic-microservice "1.0.0"
  :description "A ready to use Clojure application with a minimal configuration."
  :url "https://github.com/matheusfrantz/basic-microservice"

  :plugins [[lein-cljfmt "0.6.7"]
            [lein-kibit "0.1.8"]]

  :dependencies [[org.clojure/clojure "1.10.1"]
                 [compojure "1.6.1"]
                 [ring/ring-core "1.8.1"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-jetty-adapter "1.8.1"]
                 [ring/ring-json "0.5.0"]]

  :aliases {"lint" ["do" ["kibit"] ["cljfmt" "check"]]}

  :main basic-microservice.server)
