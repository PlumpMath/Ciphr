(ns Ciphr.schemas)

(def alphabet "abcdefghijklmnopqrstuvwxyz")

(defn add-non-letters [schema]
	(into {\space \space} schema))

(defn skew [text steps]
	(str (subs text steps) (subs text 0 steps)))

(defn caesar-schema [steps]
	(add-non-letters (into {} (map (fn [a b] [a b]) alphabet (skew alphabet steps)))))