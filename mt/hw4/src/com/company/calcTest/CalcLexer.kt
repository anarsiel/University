package com.company
import java.io.Reader
private val stringTerm: Map<Int, String> = mapOf(0 to "+",1 to "-",2 to "/",3 to "*",4 to "(",5 to ")",6 to "[",7 to "]",8 to ",")

private val RegexTerm: Map<Int, Regex> = mapOf(
9 to Regex("[0-9]+"),10 to Regex("\\s+"))
private val skipTokens = setOf(10)

object CALC_TOKENS {
val PLUS = 0
val MINUS = 1
val DIV = 2
val MUL = 3
val OPENBRACE = 4
val CLOSEBRACE = 5
val OPENSQUARE = 6
val CLOSESQUARE = 7
val COMMA = 8
val NUMBER = 9
val WS = 10
val EOF = -1
}

class CalcLexer(reader: Reader)
: BaseLexer(reader, stringTerm, RegexTerm, skipTokens, CALC_TOKENS.EOF)
