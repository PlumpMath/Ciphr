(ns Ciphr.core
	(:require 
		[Ciphr.basics]
		[Ciphr.schemas]))

(defn get-char [schema c]
	(let [item (find schema c)]
		(if (nil? item)
			"?"
			(val item))))

(defn substitute [text schema]
	(clojure.string/join (map (partial get-char schema) text)))

(defn -main [& args]
	;(assert (not (nil? (first args))) "Must supply a command line argument")
	(println (substitute (first args) (Ciphr.schemas/caesar-schema 3))))

