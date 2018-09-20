(ns user
  (:require [duct.core.env :as e]
            [ragtime.jdbc :as jdbc]
            [ragtime.repl :as repl]))

(defn dev
  "Load and switch to the 'dev' namespace."
  []
  (require 'dev)
  (in-ns 'dev)
  :loaded)

(def migration-db-urls
  {"dev" (e/env "DEV_DB_URL")
   "test" (e/env "TEST_DB_URL")})

(defn- migration-config [env]
  {:datastore  (jdbc/sql-database {:connection-uri (migration-db-urls env)})
   :migrations (jdbc/load-resources "migrations")})

(defn migrate [env]
  (repl/migrate (migration-config env)))

(defn rollback [env]
  (repl/rollback (migration-config env)))
