(defn operation [op, & operations]
  (fn [map]
    (apply op (mapv #(% map) operations))))

(defn operationFactory [op]
  (partial operation op))

(def add (operationFactory +))
(def subtract (operationFactory -))
(def multiply (operationFactory *))

(defn divWithZero [& values]
  (reduce (fn [x y] (/ x (double y))) values))

(def divide (operationFactory divWithZero))
(def negate (operationFactory -))

(defn avg [& operations]
  (fn [map]
    (/ (apply + (mapv #(% map) operations))
       (count (mapv #(% map) operations)))))

(defn med [& operations]
  (fn [map]
    (nth (vec(sort (mapv #(% map) operations)))
         (int (Math/floor (/ (count (mapv #(% map) operations)) 2))))))

;(defn constant [a]
;  (fn [& _] a))
(def constant constantly)
(defn variable [x]
  (fn [map] (get map x)))

(def operations {'+ add
                 '- subtract
                 '* multiply
                 '/ divide
                 'negate negate
                 'avg avg
                 'med med})

(defn parseList[s]
  (if (list? s)
    (if (empty? s)
      (vector)
      (if (contains? operations (first s))
        (apply (get operations (first s)) (map parseList (rest s)))
        (if (number? (first s))
          (constant (first s))
          (variable (str (first s))))))
    (if (number? s)
      (constant s)
      (variable (str s)))))
(defn parseFunction[s]
  (parseList (read-string s)))