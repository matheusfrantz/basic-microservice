(ns basic-microservice.controller.account
  (:require [basic-microservice.database.account :as database.account]
            [basic-microservice.exception :as exception]
            [basic-microservice.logic.account :as logic.account]
            [ring.util.response :refer [bad-request not-found response]]))

(defn create-account!
  [request storage]
  (let [name (get-in request [:body :name])]
    (if-not name
      (bad-request exception/bad-request)
      (let [account (logic.account/new-account name)]
        (database.account/create-account! account storage)
        (response account)))))

(defn get-account
  [request storage]
  (let [id      (get-in request [:params :id])
        account (database.account/get-account id storage)]
    (if-not account
      (not-found exception/not-found)
      (response account))))

(defn delete-account!
  [request storage]
  (let [id      (get-in request [:params :id])
        account (database.account/get-account id storage)]
    (if-not account
      (not-found exception/not-found)
      (do
        (database.account/delete-account! id storage)
        {:status 204}))))
