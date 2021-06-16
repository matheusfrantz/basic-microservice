(ns integration.account-test
  (:require [basic-microservice.component :as component]
            [basic-microservice.service :as service]
            [clojure.test :refer :all]
            [integration.helper :as helper]
            [ring.mock.request :as mock]))

(def account (helper/generate-body {:name "John"}))
(def system (component/start-system!))
(def handler (service/handler (:storage system)))

(deftest ^:integration new-account-test
  (let [response (handler (-> (mock/request :post
                                            "/account"
                                            account)
                              (mock/content-type "application/json")))
        body     (helper/parse-body (:body response))
        id       (:id body)]

    (is (= 200 (:status response)))
    (is (= "John" (:name body)))

    (let [response (handler (mock/request :get
                                          (str "/account/" id)))
          body     (helper/parse-body (:body response))]

      (is (= 200 (:status response)))
      (is (= "John" (:name body))))

    (let [response (handler (mock/request :delete
                                          (str "/account/" id)))]

      (is (= 204 (:status response))))))

(deftest ^:integration invalid-account-test
  (let [response (handler (-> (mock/request :post
                                            "/account"
                                            {})
                              (mock/content-type "application/json")))]

    (is (= 400 (:status response))))

  (let [response (handler (mock/request :get
                                        "/account/123"))]

    (is (= 404 (:status response))))

  (let [response (handler (mock/request :delete
                                        "/account/123"))]

    (is (= 204 (:status response)))))
