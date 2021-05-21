(ns integration.account-test
  (:require [ring.mock.request :as mock]
            [basic-microservice.service :as service]
            [clojure.test :refer :all]
            [integration.helper :as helper]))

(def account (helper/generate-body {:name "John"}))

(deftest ^:integration new-account-test
  (let [response (service/handler (-> (mock/request :post
                                                    "/account"
                                                    account)
                                      (mock/content-type "application/json")))
        body     (helper/parse-body (:body response))
        id       (:id body)]

    (is (= (:status response) 200))
    (is (= (:name body) "John"))

    (let [response (service/handler (mock/request :get
                                                  (str "/account/" id)))
          body     (helper/parse-body (:body response))]

      (is (= (:status response) 200))
      (is (= (:name body) "John")))

    (let [response (service/handler (mock/request :delete
                                                  (str "/account/" id)))]

      (is (= (:status response) 204)))))

(deftest ^:integration invalid-account-test
  (let [response (service/handler (-> (mock/request :post
                                                    "/account"
                                                    {})
                                      (mock/content-type "application/json")))]

    (is (= (:status response) 400)))

  (let [response (service/handler (mock/request :get
                                                "/account/123"))]

    (is (= (:status response) 404)))

  (let [response (service/handler (mock/request :delete
                                                "/account/123"))]

    (is (= (:status response) 404))))
