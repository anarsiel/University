(defn isVector [vector] (and (vector? vector) (every? number? vector)))
(defn isMatrix [matrix] (and (vector? matrix) (let [matrix (vec matrix)] (every? isVector matrix))))

(defn makeEmpty [obj] (if (number? obj) [] (if (vector? obj) (mapv makeEmpty obj) obj)))
(defn checkTensor [obj emp]
  (or (isVector obj)
      (and (vector? obj)
           (apply = emp)
           (every? true? (mapv checkTensor obj emp)))))
(defn isTensor [obj]
  (checkTensor obj (makeEmpty obj)))

(defn vectorFactory [value, length] {:pre [(and (number? value) (number? length))]} (vec (repeat length value)))
(defn matrixFactory [value, length, height] {:pre [number? length]} (vec (repeat length (vectorFactory value height))))

(defn abstractBinaryVectorOperation [operation, vector1, vector2]
  {:pre [(and (isVector vector1) (isVector vector2) (= (count vector1) (count vector2)))]}
  (mapv operation vector1 vector2))
(defn abstractPolynaryVectorOperation [operation, vectors]
  (reduce #(abstractBinaryVectorOperation operation %1 %2) vectors))

(defn abstractBinaryMatrixOperation [operation, matrix1, matrix2]
  {:pre [(and (isMatrix matrix1) (isMatrix matrix2) (= (count matrix1) (count matrix2))
              (if (= (count matrix1) 0) true (= (count (first matrix1)) (count (first matrix2)))))]}
  (mapv operation matrix1 matrix2))
(defn abstractPolynaryMatrixOperation [operation, matrixes]
  (reduce #(abstractBinaryMatrixOperation operation %1 %2) matrixes))

(defn v+ [& vectors] (abstractPolynaryVectorOperation + (vec vectors)))
(defn v* [& vectors] (abstractPolynaryVectorOperation * (vec vectors)))
(defn v*s [vector, & scalar] (v* vector (vectorFactory (apply * scalar) (count vector))))
(defn v- [& vectors] (if (== (count vectors) 1) (v*s (first vectors) -1) (abstractPolynaryVectorOperation - (vec vectors))))
(defn scalar [& vectors] (reduce + (reduce v* vectors)))

(defn binaryVectorCrossProduct [vector1, vector2]
  {:pre [(and (isVector vector1) (isVector vector2)) (== (count vector1) (count vector2))]}
  [(- (* (nth vector1 1) (nth vector2 2)) (* (nth vector1 2) (nth vector2 1)))
   (- (* (nth vector1 2) (nth vector2 0)) (* (nth vector1 0) (nth vector2 2)))
   (- (* (nth vector1 0) (nth vector2 1)) (* (nth vector1 1) (nth vector2 0)))])
(defn vect [& vectors] (reduce binaryVectorCrossProduct vectors))

(defn m+ [& matrixes] (abstractPolynaryMatrixOperation v+ (vec matrixes)))
(defn m* [& matrixes] (abstractPolynaryMatrixOperation v* (vec matrixes)))
(defn m*s [matrix, & scalars] (m* matrix (matrixFactory (apply * scalars) (count matrix) (count (first matrix)))))
(defn m- [& matrixes] (if (== (count matrixes) 1) (m*s (first matrixes) -1)(abstractPolynaryMatrixOperation v- (vec matrixes))))
(defn m*v [matrix vector] (mapv (partial scalar vector) matrix))

(defn transpose [matrix] (apply mapv vector matrix))
(defn binaryMatrixMupltiply [matrix1, matrix2] {:pre [(and (isMatrix matrix1) (isMatrix matrix2))]}
  (transpose (mapv #(m*v matrix1 %) (transpose matrix2))))

(defn m*m [& matrixes] (reduce binaryMatrixMupltiply matrixes))

(defn abstractPolynaryTensorOperation [operation & tensor] {:pre [(isTensor (vec tensor))]}
  (if (number? (first tensor))
    (apply operation tensor)
    (apply mapv (partial abstractPolynaryTensorOperation operation) tensor)))

(defn t+ [& tensor] (apply (partial abstractPolynaryTensorOperation +) tensor))
(defn t- [& tensor] (apply (partial abstractPolynaryTensorOperation -) tensor))
(defn t* [& tensor] (apply (partial abstractPolynaryTensorOperation *) tensor))