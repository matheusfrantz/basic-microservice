(ns basic-microservice.integration.helper
  (:require [cheshire.core :as json]))

(defn generate-body [body]
  (json/generate-string body))

(defn parse-body [body]
  (json/parse-string body true))
