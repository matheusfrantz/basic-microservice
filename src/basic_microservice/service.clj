(ns basic-microservice.service
  (:require [basic-microservice.controller.account :as controller.account]
            [compojure.core :refer [defroutes GET POST]]
            [compojure.route :as route]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]))

(def not-found-response {:status 404
                         :error  "not-found"})

(defroutes routes
           (POST "/account" [] controller.account/create-account!)
           (GET "/account/:id" [] controller.account/get-account)
           (route/not-found {:body not-found-response}))

(def handler
  (-> routes
      (wrap-json-body {:keywords? true})
      (wrap-json-response)
      (wrap-defaults api-defaults)))
