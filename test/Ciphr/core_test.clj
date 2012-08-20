(ns Ciphr.core-test
  (:use clojure.test)
  (:require
		[Ciphr.schemas]
		[Ciphr.core]))

(deftest generate-simple-schema
	(let [schema (Ciphr.schemas/caesar-schema 1)]
  		(is (= (val (find schema \a)) \b))
  		(is (= (val (find schema \z)) \a))
  		(is (= (val (find schema \space)) \space))))

(deftest count-nr-of-e
	(is (= 3 (Ciphr.core/count-letter "yes indeed" \e))))