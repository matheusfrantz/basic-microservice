(ns basic-microservice.exception)

(def bad-request {:status 400
                  :error  "bad-request"})

(def not-found {:status 404
                :error  "not-found"})
