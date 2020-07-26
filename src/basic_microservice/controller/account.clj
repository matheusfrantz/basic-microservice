(ns basic-microservice.controller.account
  (:require [basic-microservice.database.account :as database.account]
            [basic-microservice.logic.account :as logic.account]
            [ring.util.response :refer [bad-request not-found response]]))

(def bad-request-response {:status 400
                           :error  "bad-request"})

(def not-found-response {:status 404
                         :error  "not-found"})

(defn create-account!
  [request]
  (let [name (get-in request [:body :name])]
    (if-not name
      (bad-request bad-request-response)
      (let [account (logic.account/new-account name)]
        (database.account/create-account! account)
        (response account)))))

(defn get-account
  [request]
  (let [id      (get-in request [:params :id])
        account (first (database.account/get-account id))]
    (if-not account
      (not-found not-found-response)
      (response account))))
