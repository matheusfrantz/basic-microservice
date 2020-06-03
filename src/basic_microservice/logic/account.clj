(ns basic-microservice.logic.account
  (:import [java.util UUID]))

(defn new-account
  [name]
  {:id   (UUID/randomUUID)
   :name name})
