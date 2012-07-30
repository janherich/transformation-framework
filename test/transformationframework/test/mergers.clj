(ns transformationframework.test.mergers
  (:use [transformationframework.mergers])
  (:use [clojure.test]))

(def test-record1 {:jouid 1 :treeid 1 :amount 10.05})

(def test-record2 {:jouid 2 :treeid 1 :amount 12.25})

(def test-record3 {:jouid 3 :treeid 1 :amount 17.09})

(def test-recordlist (list test-record1 test-record2 test-record3))

(def test-keytemplate {:treeid value})

(def test-mergetemplate {:amount +})

(deftest test-count
  (is (= 3 (count test-recordlist))))

(deftest test-first-item
  (is (= {:jouid 1 :treeid 1 :amount 10.05} (first test-recordlist))))

(deftest test-value
  (is (= 20 (value 20))))

(deftest test-create-record-key
  (is (= {:treeid 1} (create-record-key test-record1 test-keytemplate))))

(deftest test-divide-groups
  (is (= 1 (count (divide-groups test-recordlist test-keytemplate)))))

(deftest test-first-divide-groups
  (is (= 3 (count (val (first (divide-groups test-recordlist test-keytemplate)))))))

(deftest test-merge-group
  (is (= {:treeid 1 :am 3 :matnam '("alco" "sl")} 
         (merge-group {:treeid 1} 
                      '({:treeid 1 :am 1 :matnam "alco"} {:treeid 1 :am 2 :matnam "sl"}) 
                      {:am + :matnam list}))))

(deftest test-merge-records
  (is (= '({:treeid 1 :amount 39.39}) (merge-records test-recordlist test-keytemplate test-mergetemplate))))

(deftest test-subrecordlist
  (is (= '({:treeid 1 :amount 2} {:treeid 1 :amount 3}) ((subrecordlist :treeid :amount) {:treeid 1 :amount 2 :tax 100} {:treeid 1 :amount 3 :tax 100}))))