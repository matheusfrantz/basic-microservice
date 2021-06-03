(ns basic-microservice.component.storage
  (:require [basic-microservice.protocol.storage :as protocol.storage]
            [com.stuartsierra.component :as component]))

(defrecord Storage [storage]
  component/Lifecycle
  (start [component]
    (assoc component :storage (atom [])))

  (stop [component]
    (assoc component :storage nil))

  protocol.storage/Storage
  (fetch [_ key] (first (filter #(= key (:id %)) @storage)))

  (store! [_ element] (swap! storage conj element))

  (delete! [_ key] (swap! storage #(remove (fn [element] (= key (:id element))) %))))

(defn new-storage []
  (map->Storage {}))
