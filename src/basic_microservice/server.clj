(ns basic-microservice.server
  (:require [basic-microservice.component :as component]))

(defn -main
  []
  (component/start-system!))
