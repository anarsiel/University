package com.company
@Suppress("UNUSED_VARIABLE")
class PythonParser(private val lexer: PythonLexer) {

private fun skip(token: Int): String {
if (lexer.token != token) throw Exception("Expected token not found:(")
val res = lexer.tokenValue ?: throw IllegalArgumentException("Cannot skip EOF token")
lexer.next()
return res
}

private fun FunHeader() : Unit = when(lexer.token) {
TOKENS.DEF -> {
val DEF = skip(TOKENS.DEF)
val NAME = skip(TOKENS.NAME)
val OPENBRACE = skip(TOKENS.OPENBRACE)
val paramList = ParamList()
val CLOSEBRACE = skip(TOKENS.CLOSEBRACE)
val EQ = skip(TOKENS.EQ)
}
else -> throw Exception("expected token not found"
)}

private fun ParamList() : Unit = when(lexer.token) {
TOKENS.NAME -> {
val nonEmptyParamList = NonEmptyParamList()
}
TOKENS.CLOSEBRACE -> {
}
else -> throw Exception("expected token not found"
)}

private fun NonEmptyParamList() : Unit = when(lexer.token) {
TOKENS.NAME -> {
val NAME = skip(TOKENS.NAME)
}
TOKENS.NAME -> {
val NAME = skip(TOKENS.NAME)
val DOT = skip(TOKENS.DOT)
val nonEmptyParamList = NonEmptyParamList()
}
else -> throw Exception("expected token not found"
)}

fun parse() : Unit {
lexer.next()
return FunHeader()
}
}
