(ns Ciphr.schemas)

(def normal-alphabet "abcdefghijklmnopqrstuvwxyz")
(def sorted-alphabet "etaoinshrdlcumwfgypbvkjxqz")

(defn add-non-letters [schema]
	(let [non-letters [\space \. \, \n \t]
		  non-letter-map (zipmap non-letters non-letters)]
			(into non-letter-map schema)))

(defn skew [text steps]
  (let [t (mod steps (count text))]
	(str (subs text t) (subs text 0 t))))

(defn caesar-schema 
	([steps]
		(caesar-schema normal-alphabet steps))
	([alphabet steps]
		(add-non-letters (zipmap alphabet (skew alphabet steps)))))

