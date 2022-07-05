package com.company
import java.io.Reader
private val stringTerm: Map<Int, String> = mapOf(0 to "def",1 to ",",2 to "(",3 to ")",4 to "=",5 to "[a-zA-Z0-9]+")

private val RegexTerm: Map<Int, Regex> = mapOf(
6 to Regex("\\s+"))
private val skipTokens = setOf(6)

object TOKENS {
val DEF = 0
val DOT = 1
val OPENBRACE = 2
val CLOSEBRACE = 3
val EQ = 4
val NAME = 5
val WS = 6
val EOF = -1
}

class PythonLexer(reader: Reader)
: BaseLexer(reader, stringTerm, RegexTerm, skipTokens, TOKENS.EOF)
