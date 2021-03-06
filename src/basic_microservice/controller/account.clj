(ns basic-microservice.controller.account
  (:require [basic-microservice.database.account :as database.account]
            [basic-microservice.logic.account :as logic.account]))

(defn create-account!
  [name storage]
  (let [account (logic.account/new-account name)]
    (database.account/create-account! account storage)))

(defn get-account
  [id storage]
  (database.account/get-account id storage))

(defn delete-account!
  [id storage]
  (database.account/delete-account! id storage))
