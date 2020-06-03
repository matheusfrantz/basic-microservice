(ns basic-microservice.server
  (:require [basic-microservice.service :as service])
  (:use ring.adapter.jetty))

(defn -main
  []
  (run-jetty service/handler {:port  3000
                              :join? false}))
