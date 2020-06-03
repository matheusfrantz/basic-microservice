(ns basic-microservice.controller.account
  (:require [basic-microservice.database.account :as database.account]
            [basic-microservice.logic.account :as logic.account]))

(defn create-account!
  [name]
  (let [account (logic.account/new-account name)]
    (database.account/create-account! name)
    account))
