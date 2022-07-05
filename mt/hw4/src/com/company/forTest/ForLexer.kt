package com.company
import java.io.Reader
private val stringTerm: Map<Int, String> = mapOf(0 to "for",1 to ";",2 to "(",3 to ")",4 to "=",5 to "++",6 to "--",7 to "<",8 to ">",9 to "==",10 to "int",11 to "double")

private val RegexTerm: Map<Int, Regex> = mapOf(
12 to Regex("[0-9]+"),13 to Regex("[a-zA-Z0-9]+"),14 to Regex("\\s+"))
private val skipTokens = setOf(14)

object FOR_TOKENS {
val FOR = 0
val SEMICOLON = 1
val OPENBRACE = 2
val CLOSEBRACE = 3
val EQ = 4
val INC = 5
val DEC = 6
val LESS = 7
val GREATER = 8
val DOUBLEEQ = 9
val INT = 10
val DOUBLE = 11
val VALUE = 12
val NAME = 13
val WS = 14
val EOF = -1
}

class ForLexer(reader: Reader)
: BaseLexer(reader, stringTerm, RegexTerm, skipTokens, FOR_TOKENS.EOF)
