(ns basic-microservice.protocol.storage)

(defprotocol Storage
  (fetch [component key])
  (store! [component element])
  (delete! [component key]))
