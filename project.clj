(defproject basic-microservice "1.0.0"
  :description "A ready to use Clojure application with a minimal configuration."
  :url "https://github.com/matheusfrantz/basic-microservice"

  :plugins [[lein-kibit "0.1.8"]]

  :dependencies [[org.clojure/clojure "1.10.1"]]

  :aliases {"lint" ["kibit"]}

  :main basic-microservice.server)
