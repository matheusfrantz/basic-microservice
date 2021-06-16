(ns basic-microservice.component.storage-test
  (:require [basic-microservice.component.storage :as component.storage]
            [basic-microservice.protocol.storage :as protocol.storage]
            [com.stuartsierra.component :as component]
            [clojure.test :refer :all]))

(def account {:id   1
              :name "Mary"})

(deftest ^:default new-storage
  (testing "creates a new storage"
    (let [storage (component/start (component.storage/new-storage))]
      (is (= account (protocol.storage/store! storage account)))
      (is (= account (protocol.storage/fetch storage 1)))
      (is (empty? (protocol.storage/delete! storage 1)))
      (is (= nil (protocol.storage/fetch storage 1))))))
