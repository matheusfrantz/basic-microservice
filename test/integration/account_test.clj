(ns integration.account-test
  (:require [basic-microservice.component :as component]
            [basic-microservice.service :as service]
            [clojure.test :refer :all]
            [integration.helper :as helper]
            [ring.mock.request :as mock]))

(def account (helper/generate-body {:name "John"}))

(deftest ^:integration new-account-test
  (let [system  (component/start-system!)
        handler (service/handler (:storage system))]

    (let [response (handler (-> (mock/request :post
                                              "/account"
                                              account)
                                (mock/content-type "application/json")))
          body     (helper/parse-body (:body response))
          id       (:id body)]

      (is (= (:status response) 200))
      (is (= (:name body) "John"))

      (let [response (handler (mock/request :get
                                            (str "/account/" id)))
            body     (helper/parse-body (:body response))]

        (is (= (:status response) 200))
        (is (= (:name body) "John")))

      (let [response (handler (mock/request :delete
                                            (str "/account/" id)))]

        (is (= (:status response) 204))))))

(deftest ^:integration invalid-account-test
  (let [system  (component/start-system!)
        handler (service/handler (:storage system))]

    (let [response (handler (-> (mock/request :post
                                              "/account"
                                              {})
                                (mock/content-type "application/json")))]

      (is (= (:status response) 400)))

    (let [response (handler (mock/request :get
                                          "/account/123"))]

      (is (= (:status response) 404)))

    (let [response (handler (mock/request :delete
                                          "/account/123"))]

      (is (= (:status response) 204)))))
