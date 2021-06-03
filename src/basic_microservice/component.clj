(ns basic-microservice.component
  (:require [basic-microservice.component.http-server :as component.http-server]
            [basic-microservice.component.storage :as component.storage]
            [basic-microservice.service :as service]
            [com.stuartsierra.component :as component]))

(defn system []
  (component/system-map :http-server (component/using (component.http-server/new-http-server 3000 service/handler) [:storage])
                        :storage     (component.storage/new-storage)))

(defn start-system! []
  (component/start (system)))
