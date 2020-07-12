(defn any-args [f] #(reduce f %&))
(def my-divide (any-args #(/ %1 (double %2))))
(def my-pow (any-args #(Math/pow (double %1) (double %2))))
(def my-log (any-args #(/ (Math/log (Math/abs %2))
                          (Math/log (Math/abs %1)))))
(defn very [f v] (f (f v)))

(definterface Expression
  (^Number toEval [vars])
  (^String toStr [])
  (^Object toDiff [x])
  (^String toInfix []))

(deftype JConstant [a]
  Expression
  (toEval [this _] (.a this))
  (toStr [this] (if (integer? (.a this))
                  (str (.a this))
                  (format "%.1f" (.a this))))
  (toDiff [_ _] (JConstant. 0))
  (toInfix [this] (.toStr this)))
(defn Constant [a] (JConstant. a))

(def zero (Constant 0))
(def one (Constant 1))
(def neg (Constant -1))
(def two (Constant 2))

(deftype JVariable [a]
  Expression
  (toEval [this vars] (vars (.a this)))
  (toStr [this] (.a this))
  (toDiff [this var] (if (= var (.a this)) one zero))
  (toInfix [this] (.toStr this)))
(defn Variable [a] (JVariable. a))

(deftype JAbstract [f ch args dif]
  Expression
  (toEval [this vars]
    (apply (.f this) (map #(.toEval % vars) (.args this))))
  (toStr [this]
    (str "(" (clojure.string/join " " (cons (.ch this) (map #(.toStr %) (.args this)))) ")"))
  (toDiff [this var]
    (second (apply (.dif this)
                   (map list (.args this)
                        (map #(.toDiff % var) (.args this))))))
  (toInfix [this]
    (let [inf (map #(.toInfix %) (.args this)) c (.ch this)]
      (if (> (count inf) 1)
        (str "(" (first inf) " " c " " (second inf) ")")
        (str c "(" (first inf) ")")))))

(defn JOperation [f ch dif] #(JAbstract. f ch %& (any-args dif)))

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

(def Pow (JOperation my-pow '** (list () ())))
(def Log (JOperation my-log (symbol "//") (list () ())))

(def Negate (JUnary - 'negate (constantly neg)))
(declare Cosh)
(defn Sinh [a] ((JUnary #(Math/sinh %) 'sinh Cosh) a))
(def Cosh (JUnary #(Math/cosh %) 'cosh Sinh))

(defn evaluate [this vars] (.toEval this vars))
(defn toString [this] (.toStr this))
(defn diff [this var] (.toDiff this var))
(defn toStringInfix [this] (.toInfix this))

(def operations
  {'+ Add '- Subtract '* Multiply '/ Divide 'negate Negate
   (symbol "//") Log '** Pow})

(defn parsePrefix [exp]
  (cond (list? exp) (apply (operations (first exp))
                           (map parsePrefix (rest exp)))
        (number? exp) (Constant exp)
        :else (Variable (str exp))))

(defn parseObject [exp]
  (parsePrefix (read-string exp)))

; ------------------

(defn -return [value tail] {:value value :tail tail})
(def -valid? boolean)
(def -value :value)
(def -tail :tail)

(defn _show [result]
  (if (-valid? result)
    (str "-> "
         (pr-str (-value result)) " | "
         (pr-str (apply str (-tail result))))
    "!"))
(defn tabulate [parser inputs]
  (run! (fn [input] (printf "    %-10s %s\n" input (_show (parser input)))) inputs))

(defn _empty [value] (partial -return value))
(defn _char [p] (fn [[c & cs]] (if (and c (p c)) (-return c cs))))
(defn _map [f] (fn [result] (if (-valid? result)
                              (-return (f (-value result)) (-tail result)))))
(defn _combine [f a b]
  (fn [str]
    (let [ar ((force a) str)]
      (if (-valid? ar)
        ((_map (partial f (-value ar)))
          ((force b) (-tail ar)))))))
(defn _either [a b]
  (fn [str] (let [ar ((force a) str)]
              (if (-valid? ar) ar ((force b) str)))))
(defn _try [fake origin]
  (fn [str] (if (not (-valid? ((force fake) str)))
              ((force origin) str))))

(defn _parser [p]
  (fn [input]
    (-value ((_combine (fn [v _] v) p (_char #{\u0000})) (str input \u0000)))))
(defn +char [chars] (_char (set chars)))
(defn +char-not [chars] (_char (comp not (set chars))))
(defn +map [f parser] (comp (_map f) parser))
(def +try _try)
(def +parser _parser)

(def +ignore (partial +map (constantly (quote ignore))))
(defn iconj [coll value] (if (= value (quote ignore)) coll (conj coll value)))
(defn +seq [& ps] (reduce (partial _combine iconj) (_empty []) ps))
(defn +seqf [f & ps] (+map (partial apply f) (apply +seq ps)))
(defn +seqn [n & ps] (apply +seqf (fn [& vs] (nth vs n)) ps))

(defn +or [p & ps] (reduce (partial _either) p ps))
(defn +opt [p] (+or p (_empty nil)))
(defn +star [p] (letfn [(rec [] (+or (+seqf cons p (delay (rec))) (_empty ())))] (rec)))
(defn +plus [p] (+seqf cons p (+star p)))
(defn +str [p] (+map (partial apply str) p))
(defn +string [s] (+str (apply +seq (map (comp +char str) (char-array s)))))

(def *ws (+ignore (+star (+char " \t\n\r"))))
(def *digit (+char "0123456789"))
(def *number
  (+map (comp Constant read-string) (+str (+seqf concat
                                                 (+seqf cons (+opt (+char "-")) (+plus *digit))
                                                 (+seqf cons (+opt (+char ".")) (+star *digit))))))
(defn *operator
  [fakes ops]
  (+map symbol (apply +or (if (empty? fakes)
                            (map +string ops)
                            (map #(+try (+string %1) (+string %2)) fakes ops)))))
(def *all-chars (mapv char (range 32 128)))
(def *letter (+char (apply str (filter #(Character/isLetter %) *all-chars))))
(def *variable (+map (comp Variable str) (+char "xyz")))

(declare add-sub)
(def unary
    (delay (+seqn 0 *ws (+or (+seqn 1 (+char "(") add-sub (+char ")"))
                             (+map #(Negate (second %))
                                   (+seq (*operator [] ["negate"]) unary))
                             *number *variable) *ws)))
(defn some-assoc [is-left]
  (fn [a] (let [ra (if is-left a (reverse a))]
            (reduce #(apply (operations (first %2))
                            (if is-left [%1 (second %2)]
                                        [(second %2) %1]))
                    (first ra) (partition 2 (rest ra))))))
(defn abstract [next fakes ops type]
  (+map (some-assoc type) (+seqf cons next
                                 (+map (partial apply concat)
                                       (+star (+seq (*operator fakes ops) next))))))
(def pow-log (abstract unary [] ["**" "//"] false))
(def mul-div (abstract pow-log ["**" "//"] ["*" "/"] true))
(def add-sub (abstract mul-div [] ["+" "-"] true))

(defn parseObjectInfix [exp]
  ((+parser add-sub) exp))