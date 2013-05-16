(ns stencil.test.core
  (:use clojure.test
        stencil.core))

;; Test case to make sure we don't get a regression on inverted sections with
;; list values for a name.

(deftest inverted-section-list-key-test
  (is (= ""
         (render-string "{{^a}}a{{b}}a{{/a}}" {:a [:b "11"]})))
  (is (= ""
         (render-string "{{^a}}a{{b}}a{{/a}}" {"a" ["b" "11"]}))))

;; Test that lambdas can access full context.

(deftest pass-full-context-to-lambda-test
  (is (= "b"
         (render-string "{{#a?}}{{lambda}}{{/a?}}"
                        {:a? true
                         :b "b"
                         :lambda ^{:stencil/pass-context true}
                         (fn [ctx] (render-string "{{b}}" ctx))}))))
