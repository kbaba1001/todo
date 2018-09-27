(ns todo.test-utils
  (:require [duct.database.sql :refer :all]
            [duct.core.env :as e]
            [clojure.java.jdbc :as jdbc]))

(def db-spec
  {:connection (jdbc/get-connection {:connection-uri (e/env "TEST_DB_URL")})})

(def db (->Boundary db-spec))

(defn drop-all-tables []
  (let [table-names (map :relname (jdbc/query db-spec ["SELECT relname FROM pg_stat_user_tables WHERE relname <> 'ragtime_migrations';"]))]
    (doseq [t table-names]
      (jdbc/delete! db-spec t []))))

(defn db-creanup [test-fn]
  (test-fn)
  (drop-all-tables))
