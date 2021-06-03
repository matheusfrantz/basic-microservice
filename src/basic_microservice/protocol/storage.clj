(ns basic-microservice.protocol.storage)

(defprotocol Storage
  (fetch [component key])
  (store! [component object])
  (delete! [component key]))
