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
	(let [flag (first args)]
		(cond 
			(= flag "-encrypt") 
				(println (substitute (second args) (Ciphr.schemas/caesar-schema (Integer/parseInt (nth args 2)))))
			:else (println "Please supply a argument: \n -encrypt [string] [steps]"))))

