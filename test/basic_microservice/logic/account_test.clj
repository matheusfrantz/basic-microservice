(ns basic-microservice.logic.account-test
  (:require [basic-microservice.logic.account :as logic.account]
            [clojure.test :refer :all]))

(deftest ^:default new-account-test
  (testing "creates a new account"
    (let [name    "John"
          account (logic.account/new-account name)]
      (is (some? account))
      (is (some? (:id account)))
      (is (= (:name account) name)))))
