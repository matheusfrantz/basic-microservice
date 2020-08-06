(ns basic-microservice.database.account)

(def account-state (atom []))

(defn create-account!
  [account]
  (swap! account-state conj account))

(defn get-account
  [id]
  (first (filter #(= id (:id %)) @account-state)))

(defn delete-account!
  [id]
  (swap! account-state #(remove (fn [account] (= id (:id account))) %)))
