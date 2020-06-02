(ns basic-microservice.database.account)

(def account-state (atom []))

(defn create-account!
  [account]
  (swap! account-state conj account))
