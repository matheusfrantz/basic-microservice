(ns basic-microservice.server
  (:use ring.adapter.jetty))

(defn handler
  [request]
  {:status  200
   :headers {"Content-Type" "text/plain"}
   :body    "Hello, world!"})

(defn -main
  []
  (run-jetty handler {:port 3000}))
