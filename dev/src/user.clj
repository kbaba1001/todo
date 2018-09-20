(ns user
  (:require [ragtime.jdbc :as jdbc]
            [ragtime.repl :as repl]))

(defn dev
  "Load and switch to the 'dev' namespace."
  []
  (require 'dev)
  (in-ns 'dev)
  :loaded)

(defn- migration-config []
  {:datastore  (jdbc/sql-database {:connection-uri "jdbc:postgresql://db:5432/todo-development?user=postgres&password="})
   :migrations (jdbc/load-resources "migrations")})

(defn migrate []
  (repl/migrate (migration-config)))

(defn rollback []
  (repl/rollback (migration-config)))
