(ns basic-microservice.service
  (:require [basic-microservice.controller.account :as controller.account]
            [basic-microservice.exception :as exception]
            [compojure.core :refer [DELETE GET POST]]
            [compojure.route :as route]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.util.response :refer [bad-request not-found response]]))

(defn create-account
  [name storage]
  (if name
    (let [account (controller.account/create-account! name storage)]
      (response account))
    (bad-request exception/bad-request)))

(defn get-account
  [id storage]
  (if-let [account (controller.account/get-account id storage)]
    (response account)
    (not-found exception/not-found)))

(defn delete-account
  [id storage]
  (controller.account/delete-account! id storage)
  {:status 204})

(defn routes [storage]
  (compojure.core/routes
   (POST "/account" {{:keys [name]} :body} (create-account name storage))
   (GET "/account/:id" [id] (get-account id storage))
   (DELETE "/account/:id" [id] (delete-account id storage))
   (route/not-found {:body exception/not-found})))

(defn handler [storage]
  (-> storage
      (routes)
      (wrap-json-body {:keywords? true})
      (wrap-json-response)
      (wrap-defaults api-defaults)))
