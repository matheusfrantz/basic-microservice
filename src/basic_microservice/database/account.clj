(ns basic-microservice.database.account)

(def account-state (atom []))

(defn create-account!
  [account]
  (swap! account-state conj account))

(defn get-account
  [id]
  (filter #(= id (get-in % [:id])) @account-state))
