(ns basic-microservice.service
  (:require [basic-microservice.controller.account :as controller.account]
            [basic-microservice.exception :as exception]
            [compojure.core :refer [DELETE GET POST]]
            [compojure.route :as route]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]))

(defn routes [storage]
  (compojure.core/routes
   (POST "/account" request (controller.account/create-account! request storage))
   (GET "/account/:id" request (controller.account/get-account request storage))
   (DELETE "/account/:id" request (controller.account/delete-account! request storage))
   (route/not-found {:body exception/not-found})))

(defn handler [storage]
  (-> storage
      (routes)
      (wrap-json-body {:keywords? true})
      (wrap-json-response)
      (wrap-defaults api-defaults)))
