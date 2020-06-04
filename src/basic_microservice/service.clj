(ns basic-microservice.service
  (:require [basic-microservice.controller.account :as controller.account]
            [compojure.core :refer [defroutes GET POST]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [ring.util.response :refer [response]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]))

(defroutes routes
           (GET "/account/:id" request
             (let [id (get-in request [:params :id])]
               (response (controller.account/get-account id))))
           (POST "/account" request
             (let [name (get-in request [:body :name])]
               (response (controller.account/create-account! name)))))

(def handler
  (-> routes
      (wrap-json-body {:keywords? true})
      (wrap-json-response)
      (wrap-defaults api-defaults)))
