grammar Grammar;

file : pckg (start rule1+)? EOF;

pckg : PACKAGE PCKG_NAME SEMICOLON;

start: START NON_TERM SEMICOLON;

rule1
	: nonTermRule SEMICOLON
	| termRule SEMICOLON
	;

nonTermRule : NON_TERM inAttrs? (':' returnAttr)? ':=' productions (PIPE productions)*;

inAttrs : '<' param (',' param)* '>';
param : paramName ':' paramType;
paramType : TERM;
paramName : NON_TERM;
returnAttr: TERM;

productions: product*;
product: NON_TERM args? | TERM | CODE;
args: '(' arg (',' arg)* ')';
arg : NON_TERM | TERM | CODE;

termRule
	: commonTermRule
	| skipRule
	;

commonTermRule : TERM '=' term_value;

skipRule : TERM ARROW term_value;


term_value
	: REGEX
	| STRING
	;


PACKAGE : 'package';
PIPE : '|';
SEMICOLON : ';';
ARROW : '->';
START : 'start';

NON_TERM : [a-z][a-zA-Z0-9]*;
TERM : [A-Z][a-zA-Z0-9]*;

REGEX : '\'' (~('\''|'\r' | '\n') | '\\\'')* '\'';
STRING : '"' (~('"') | '\\"')* '"';

CODE : '{' (~[{}]+ CODE?)* '}' ;
PCKG_NAME : ([a-z0-9] | '.')+;

WS  : [ \t\r\n]+ -> skip ;