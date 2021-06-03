(ns basic-microservice.service
  (:require [basic-microservice.controller.account :as controller.account]
            [basic-microservice.exception :as exception]
            [compojure.core :refer [DELETE GET POST]]
            [compojure.route :as route]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]))

(defn routes [storage]
  (compojure.core/routes
   (POST "/account" {{:keys [name]} :body} (controller.account/create-account! name storage))
   (GET "/account/:id" [id] (controller.account/get-account id storage))
   (DELETE "/account/:id" [id] (controller.account/delete-account! id storage))
   (route/not-found {:body exception/not-found})))

(defn handler [storage]
  (-> storage
      (routes)
      (wrap-json-body {:keywords? true})
      (wrap-json-response)
      (wrap-defaults api-defaults)))
