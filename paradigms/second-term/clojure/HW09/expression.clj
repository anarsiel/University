(use '[clojure.string :as s])

(defn my-divide [& a] (reduce #(/ %1 (double %2)) a))

(definterface Expression
  (^Number toEval [vars])
  (^String toStr [])
  (^Object toDiff [x]))

(deftype JConstant [a]
  Expression
  (toEval [this _] (.a this))
  (toStr [this] (format "%.1f" (.a this)))
  (toDiff [_ _] (JConstant. 0)))
(defn Constant [a] (JConstant. a))

(def zero (Constant 0))
(def one (Constant 1))
(def neg (Constant -1))
(def two (Constant 2))

(deftype JVariable [a]
  Expression
  (toEval [this vars] (vars (.a this)))
  (toStr [this] (.a this))
  (toDiff [this var] (if (= var (.a this)) one zero)))
(defn Variable [a] (JVariable. a))

(deftype JAbstract [f ch args dif]
  Expression
  (toEval [this vars]
    (apply (.f this) (map #(.toEval % vars) (.args this))))
  (toStr [this]
    (str "("
         (s/join " " (cons (.ch this)
                           (map #(.toStr %) (.args this))))
         ")"))
  (toDiff [this var]
    (second (apply (.dif this)
                   (map list (.args this)
                        (map #(.toDiff % var) (.args this)))))))

(defn diffOp [f] #(reduce f %&))
(defn JOperation [f ch dif] #(JAbstract. f ch %& (diffOp dif)))

(declare Multiply)
(defn JUnary [f ch dif]
  #(JAbstract. f ch %& (fn [[x dx]] (list () (Multiply (dif x) dx)))))

(def Add (JOperation + '+ (fn [[_ dx] [_ dy]] (list () (Add dx dy)))))
(def Subtract (JOperation - '- (fn [[_ dx] [_ dy]] (list () (Subtract dx dy)))))
(def Multiply
  (JOperation * '* (fn [[x dx] [y dy]]
                     (list (Multiply x y) (Add (Multiply dx y) (Multiply x dy))))))
(def Divide
  (JOperation my-divide '/
              (fn [[x dx] [y dy]]
                (list (Divide x y)
                      (Divide (Subtract (Multiply dx y) (Multiply x dy))
                              (Multiply y y))))))

(def Negate (JUnary - 'negate (constantly neg)))
(declare Cosh)
(defn Sinh [a] ((JUnary #(Math/sinh %) 'sinh Cosh) a))
(def Cosh (JUnary #(Math/cosh %) 'cosh Sinh))

(defn evaluate [this vars] (.toEval this vars))
(defn toString [this] (.toStr this))
(defn diff [this var] (.toDiff this var))

(def operations
  {'+ Add '- Subtract '* Multiply '/ Divide 'negate Negate
   'sinh Sinh 'cosh Cosh})

(defn parsePrefix [exp]

  (cond (list? exp) (apply (operations (first exp)) (map parsePrefix (rest exp)))
        (number? exp) (Constant exp)
        :else (Variable (str exp))))

(defn parseObject [exp]
  (parsePrefix (read-string exp)))