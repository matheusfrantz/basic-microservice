(ns basic-microservice.controller.account
  (:require [basic-microservice.database.account :as database.account]
            [basic-microservice.exception :as exception]
            [basic-microservice.logic.account :as logic.account]
            [ring.util.response :refer [bad-request not-found response]]))

(defn create-account!
  [name storage]
  (if-not name
    (bad-request exception/bad-request)
    (let [account (logic.account/new-account name)]
      (database.account/create-account! account storage)
      (response account))))

(defn get-account
  [id storage]
  (let [account (database.account/get-account id storage)]
    (if-not account
      (not-found exception/not-found)
      (response account))))

(defn delete-account!
  [id storage]
  (let [account (database.account/get-account id storage)]
    (if-not account
      (not-found exception/not-found)
      (do
        (database.account/delete-account! id storage)
        {:status 204}))))
