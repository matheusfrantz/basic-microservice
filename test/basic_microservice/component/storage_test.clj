(ns basic-microservice.component.storage-test
  (:require [basic-microservice.component.storage :as component.storage]
            [basic-microservice.model.account :as model.account]
            [basic-microservice.protocol.storage :as protocol.storage]
            [com.stuartsierra.component :as component]
            [clojure.test :refer :all]
            [schema-generators.generators :as generators]))

(deftest ^:default new-storage
  (testing "creates a new storage"
    (let [storage (component/start (component.storage/new-storage))
          account (generators/generate model.account/Account)
          id      (:id account)]
      (is (= account (protocol.storage/store! storage account)))
      (is (= account (protocol.storage/fetch storage id)))
      (is (empty? (protocol.storage/delete! storage id)))
      (is (= nil (protocol.storage/fetch storage id))))))
