(ns basic-microservice.model.account
  (:require [schema.core :as s]))

(def skeleton
  {:id   s/Uuid
   :name s/Str})

(s/defschema Account skeleton)
