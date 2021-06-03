(ns basic-microservice.database.account
  (:require [basic-microservice.protocol.storage :as protocol.storage]))

(defn create-account!
  [account storage]
  (protocol.storage/store! storage account))

(defn get-account
  [id storage]
  (protocol.storage/fetch storage id))

(defn delete-account!
  [id storage]
  (protocol.storage/delete! storage id))
