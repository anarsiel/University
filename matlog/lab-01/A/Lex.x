{
module Lex where
}

%wrapper "basic"

$digit = 0-9
$alpha = [A-Z]

tokens :-

  $white                                           ;
  \(                                                { \_ -> TParL  } 
  \)                                                { \_ -> TParR  } 
  \!                                                { \_ -> TNot   }
  \&                                                { \_ -> TCon   }
  \|                                                { \_ -> TDis   }
  \-\>                                              { \_ -> TImp   } 
  $alpha [$alpha $digit \']*                        { \s -> TVar s }


{
data Token = TParL | 
             TParR | 
             TNot  | 
             TCon  | 
             TDis  | 
             TImp  | 
             TVar String 
             deriving (Eq, Show)
}