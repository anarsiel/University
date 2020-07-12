module Main where

import Grammar
import Lex (alexScanTokens)
import Synt (synt)

main :: IO ()
main = do 
  expression <- getLine
  case synt (alexScanTokens expression) of 
    Right result -> putStrLn $ show result