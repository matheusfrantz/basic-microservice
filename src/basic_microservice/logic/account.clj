(ns basic-microservice.logic.account
  (:require [basic-microservice.model.account :as model.account]
            [schema.core :as s])
  (:import [java.util UUID]))

(defn- uuid
  []
  (str (UUID/randomUUID)))

(s/defn new-account :- model.account/Account
  [name]
  {:id   (uuid)
   :name name})
