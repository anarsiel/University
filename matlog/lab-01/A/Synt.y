{
module Synt where
import Lex
import Grammar
}

%name synt
%tokentype { Token }

%error { parseError }

%monad { Either String }{ >>= }{ return }

%token
  '->'         { TImp }
  '!'          { TNot }
  '|'          { TDis }
  '&'          { TCon }
  '('          { TParL }
  ')'          { TParR }
  Var          { TVar $$ }

%%

Base:
  Disjoin { $1 } |
  Disjoin '->' Base { MyExp Imp $1 $3 }

Disjoin:
  Conjoin { $1 } |
  Disjoin '|' Conjoin { MyExp Dis $1 $3 }

Conjoin:
  Not { $1 } |
  Conjoin '&' Not { MyExp Con $1 $3 }

Not:
  '!' Not { Not $2 } | 
  Var { Var $1 } | 
  '(' Base ')' { $2 }  

{
  parseError errorMessage = error "Error"
}
