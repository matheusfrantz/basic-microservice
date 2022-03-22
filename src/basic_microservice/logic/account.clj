(ns basic-microservice.logic.account
  (:require [basic-microservice.model.account :as model.account]
            [schema.core :as s]))

(defn- uuid
  []
  (str (random-uuid)))

(s/defn new-account :- model.account/Account
  [name]
  {:id   (uuid)
   :name name})
