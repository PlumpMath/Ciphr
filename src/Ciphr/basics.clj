(ns Ciphr.basics
	(:require clojure.string))

(def substitution-schema {
	\a \b
	\b \c
	\c \d
	\d \e
	\e \a
	})

(defn get-char [c]
	(let [item (find substitution-schema c)]
		(if (nil? item)
			"_"
			(val item))))

(defn substitution [text]
	(clojure.string/join (map get-char text)))