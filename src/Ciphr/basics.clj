(ns Ciphr.basics
	(:require clojure.string))

(defn get-char [schema c]
	(let [item (find schema c)]
		(if (nil? item)
			"?"
			(val item))))

(defn substitute [text schema]
	(clojure.string/join (map (partial get-char schema) text)))