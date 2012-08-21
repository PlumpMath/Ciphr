(ns Ciphr.core-test
  (:use clojure.test)
  (:require
		[Ciphr.schemas :as s]
		[Ciphr.core :as c]))

(deftest generate-simple-schema
	(let [schema (s/caesar-schema 1)]
  		(is (= (val (find schema \a)) \b))
  		(is (= (val (find schema \z)) \a))
  		(is (= (val (find schema \space)) \space))))

(deftest count-nr-of-e
	(is (= 3 (c/count-letter "yes indeed" \e))))

(deftest try-to-solve
	(is (= "eeeeettttaaaooi" (c/solve "kkkkkjjjjhhhggf" s/normal-alphabet s/sorted-alphabet))))