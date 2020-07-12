module Grammar where
import Data.List (intercalate)

data BinaryOperations = Imp |
                        Dis | 
                        Con

data MyExpression = MyExp BinaryOperations MyExpression MyExpression |
                          Not MyExpression |
                          Var String

instance Show BinaryOperations where
    show Imp = "->"
    show Dis = "|"
    show Con = "&"

instance Show MyExpression where
    show (MyExp op a b) = "(" ++ intercalate "," [show op, show a, show b] ++ ")"
    show (Not expression) = "(!" ++ show expression ++ ")"
    show (Var name) = name