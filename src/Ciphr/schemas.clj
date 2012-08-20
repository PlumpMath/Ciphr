(ns Ciphr.schemas)

(def normal-alphabet "abcdefghijklmnopqrstuvwxyz")
(def sorted-alphabet "etaoinshrdlcumwfgypbvkjxqz")

(defn add-non-letters [schema]
	(into {
		\space \space, 
		\. \., 
		\, \,, 
		\n \n, 
		\t \t} schema))

(defn skew [text steps]
  (let [t (mod steps (count text))]
	(str (subs text t) (subs text 0 t))))

(defn caesar-schema 
	([steps]
		(caesar-schema normal-alphabet steps))
	([alphabet steps]
		(add-non-letters (into {} (map (fn [a b] [a b]) alphabet (skew alphabet steps))))))

