(ns basic-microservice.service
  (:require [basic-microservice.controller.account :as controller.account]
            [compojure.core :refer [defroutes GET POST]]
            [compojure.route :as route]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [ring.util.response :refer [not-found response]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]))

(def not-found {:status 404
                :error  "not-found"})

(defroutes routes
           (GET "/account/:id" request
             (let [id      (get-in request [:params :id])
                   account (controller.account/get-account id)]
               (if-not account
                 (not-found not-found)
                 (response account))))
           (POST "/account" request
             (let [name (get-in request [:body :name])]
               (response (controller.account/create-account! name))))
           (route/not-found {:body not-found}))

(def handler
  (-> routes
      (wrap-json-body {:keywords? true})
      (wrap-json-response)
      (wrap-defaults api-defaults)))
