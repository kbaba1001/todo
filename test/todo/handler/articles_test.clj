(ns todo.handler.articles-test
  (:require [clojure.test :as t]
            [integrant.core :as ig]
            [shrubbery.core :as shrubbery]
            [todo.test-utils :as u]
            todo.handler.articles
            [todo.boundary.articles :as articles]))

(def database-stub
  (shrubbery/stub articles/Articles
    {:index-articles [{:id 1 :content "foo"}]
     :create-article 1}))

(t/deftest handler-articles-test
  (t/testing "GET /articles"
    (let [handler (ig/init-key :todo.handler.articles/index {:db database-stub})
          [status message] (handler {})]
      (t/is (= :ataraxy.response/ok status))
      (t/is (= [{:id 1 :content "foo"}] message))))

  (t/testing "POST /articles"
    (let [handler (ig/init-key :todo.handler.articles/create {:db database-stub})
          [status message] (handler {})]
      (t/is (= :ataraxy.response/created status))
      (t/is (= "/articles/1" message)))))
