package com.company
@Suppress("UNUSED_VARIABLE")
class ForParser(private val lexer: ForLexer) {

private fun skip(token: Int): String {
if (lexer.token != token) throw Exception("Expected token not found:(")
val res = lexer.tokenValue ?: throw IllegalArgumentException("Cannot skip EOF token")
lexer.next()
return res
}

private fun FunHeader() : Unit = when(lexer.token) {
FOR_TOKENS.FOR -> {
val SEMICOLON : MutableList<String> = mutableListOf()
val FOR = skip(FOR_TOKENS.FOR)
val OPENBRACE = skip(FOR_TOKENS.OPENBRACE)
val a = A()
SEMICOLON.add(skip(FOR_TOKENS.SEMICOLON))
val b = B()
SEMICOLON.add(skip(FOR_TOKENS.SEMICOLON))
val c = C()
val CLOSEBRACE = skip(FOR_TOKENS.CLOSEBRACE)
}
else -> throw Exception("expected token not found"
)}

private fun A() : Unit = when(lexer.token) {
FOR_TOKENS.INT, FOR_TOKENS.DOUBLE -> {
val type = Type()
val NAME = skip(FOR_TOKENS.NAME)
val EQ = skip(FOR_TOKENS.EQ)
val VALUE = skip(FOR_TOKENS.VALUE)
}
else -> throw Exception("expected token not found"
)}

private fun B() : Unit = when(lexer.token) {
FOR_TOKENS.NAME -> {
val NAME = skip(FOR_TOKENS.NAME)
val cmp = Cmp()
val VALUE = skip(FOR_TOKENS.VALUE)
}
else -> throw Exception("expected token not found"
)}

private fun C() : Unit = when(lexer.token) {
FOR_TOKENS.NAME -> {
val NAME = skip(FOR_TOKENS.NAME)
val cPrime = CPrime()
}
FOR_TOKENS.INC, FOR_TOKENS.DEC -> {
val cPrime = CPrime()
val NAME = skip(FOR_TOKENS.NAME)
}
else -> throw Exception("expected token not found"
)}

private fun CPrime() : Unit = when(lexer.token) {
FOR_TOKENS.INC -> {
val INC = skip(FOR_TOKENS.INC)
}
FOR_TOKENS.DEC -> {
val DEC = skip(FOR_TOKENS.DEC)
}
else -> throw Exception("expected token not found"
)}

private fun Cmp() : Unit = when(lexer.token) {
FOR_TOKENS.LESS -> {
val LESS = skip(FOR_TOKENS.LESS)
}
FOR_TOKENS.GREATER -> {
val GREATER = skip(FOR_TOKENS.GREATER)
}
FOR_TOKENS.DOUBLEEQ -> {
val DOUBLEEQ = skip(FOR_TOKENS.DOUBLEEQ)
}
else -> throw Exception("expected token not found"
)}

private fun Type() : Unit = when(lexer.token) {
FOR_TOKENS.INT -> {
val INT = skip(FOR_TOKENS.INT)
}
FOR_TOKENS.DOUBLE -> {
val DOUBLE = skip(FOR_TOKENS.DOUBLE)
}
else -> throw Exception("expected token not found"
)}

fun parse() : Unit {
lexer.next()
val res = FunHeader()
if (lexer.token != FOR_TOKENS.EOF) {
throw Exception("EOF expected")
}
return res
}
}
