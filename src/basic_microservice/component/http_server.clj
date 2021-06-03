(ns basic-microservice.component.http-server
  (:require [com.stuartsierra.component :as component])
  (:use ring.adapter.jetty))

(defrecord HttpServer [port handler storage]
  component/Lifecycle
  (start [component]
    (println storage)
    (assoc component :http-server (run-jetty (handler storage) {:port  port
                                                                :join? false})))

  (stop [component]
    (assoc component :http-server nil)))

(defn new-http-server [port handler]
  (map->HttpServer {:port    port
                    :handler handler}))
