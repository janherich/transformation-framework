(ns transformationframework.main
  (:require [transformationframework.functions :as fc]
            [transformationframework.mergers :as mg])
  (:gen-class))

(def test-record1 {:jouid 1 :treeid 1 :amount 10.05 :discount 1.005 :d_reason "D"})

(def test-record2 {:jouid 2 :treeid 1 :amount 12.25 :discount 1.225 :d_reason "S"})

(def test-record3 {:jouid 3 :treeid 1 :amount 17.09 :discount 1.709 :d_reason "D"})

(def test-record4 {:jouid 1 :treeid 2 :amount 15.02 :discount 1.502 :d_reason "S"})

(def test-record5 {:jouid 2 :treeid 2 :amount 7.07 :discount 0.707 :d_reason "D"})

(def test-recordlist (list test-record1 test-record2 test-record3 test-record4 test-record5))

(def test-keytemplate {:treeid identity})

(def test-mergetemplate {:amount + :jouid list :discounts (mg/subrecordlist :discount :d_reason)})

(defn -main [& args]
  (mg/merge-records test-recordlist test-keytemplate test-mergetemplate))