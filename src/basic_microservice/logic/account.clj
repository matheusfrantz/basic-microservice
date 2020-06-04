(ns basic-microservice.logic.account
  (:import [java.util UUID]))

(defn- uuid
  []
  (str (UUID/randomUUID)))

(defn new-account
  [name]
  {:id   (uuid)
   :name name})
