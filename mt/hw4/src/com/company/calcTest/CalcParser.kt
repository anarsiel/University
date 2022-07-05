package com.company
@Suppress("UNUSED_VARIABLE")
class CalcParser(private val lexer: CalcLexer) {

private fun skip(token: Int): String {
if (lexer.token != token) throw Exception("Expected token not found:(")
val res = lexer.tokenValue ?: throw IllegalArgumentException("Cannot skip EOF token")
lexer.next()
return res
}

private fun Expr() : Complex = when(lexer.token) {
CALC_TOKENS.OPENBRACE, CALC_TOKENS.OPENSQUARE -> {
val term = Term()
val exprs = Exprs(term)
 exprs 
}
else -> throw Exception("expected token not found"
)}

private fun Exprs(acc: Complex) : Complex = when(lexer.token) {
CALC_TOKENS.PLUS -> {
val PLUS = skip(CALC_TOKENS.PLUS)
val term = Term()
 val next = plus(acc, term) 
val exprs = Exprs(next)
exprs
}
CALC_TOKENS.MINUS -> {
val MINUS = skip(CALC_TOKENS.MINUS)
val term = Term()
 val next = minus(acc, term) 
val exprs = Exprs(next)
exprs
}
CALC_TOKENS.EOF, CALC_TOKENS.CLOSEBRACE -> {
acc
}
else -> throw Exception("expected token not found"
)}

private fun Term() : Complex = when(lexer.token) {
CALC_TOKENS.OPENBRACE, CALC_TOKENS.OPENSQUARE -> {
val factor = Factor()
val terms = Terms(factor)
terms
}
else -> throw Exception("expected token not found"
)}

private fun Terms(acc: Complex) : Complex = when(lexer.token) {
CALC_TOKENS.MUL -> {
val MUL = skip(CALC_TOKENS.MUL)
val factor = Factor()
val terms = Terms(mul(acc, factor))
terms
}
CALC_TOKENS.DIV -> {
val DIV = skip(CALC_TOKENS.DIV)
val factor = Factor()
val terms = Terms(div(acc, factor))
terms
}
CALC_TOKENS.PLUS, CALC_TOKENS.MINUS, CALC_TOKENS.EOF, CALC_TOKENS.CLOSEBRACE -> {
acc
}
else -> throw Exception("expected token not found"
)}

private fun Factor() : Complex = when(lexer.token) {
CALC_TOKENS.OPENBRACE, CALC_TOKENS.OPENSQUARE -> {
val single = Single()
val factors = Factors(single)
factors
}
else -> throw Exception("expected token not found"
)}

private fun Factors(acc: Complex) : Complex = when(lexer.token) {
CALC_TOKENS.MUL, CALC_TOKENS.DIV, CALC_TOKENS.PLUS, CALC_TOKENS.MINUS, CALC_TOKENS.EOF, CALC_TOKENS.CLOSEBRACE -> {
acc
}
else -> throw Exception("expected token not found"
)}

private fun Single() : Complex = when(lexer.token) {
CALC_TOKENS.OPENBRACE -> {
val OPENBRACE = skip(CALC_TOKENS.OPENBRACE)
val expr = Expr()
val CLOSEBRACE = skip(CALC_TOKENS.CLOSEBRACE)
expr
}
CALC_TOKENS.OPENSQUARE -> {
val complex = Complex()
complex
}
else -> throw Exception("expected token not found"
)}

private fun Complex() : Complex = when(lexer.token) {
CALC_TOKENS.OPENSQUARE -> {
val OPENSQUARE = skip(CALC_TOKENS.OPENSQUARE)
val NUMBER = skip(CALC_TOKENS.NUMBER)
val COMMA = skip(CALC_TOKENS.COMMA)
val complexTail = ComplexTail(NUMBER.toDouble())
complexTail
}
else -> throw Exception("expected token not found"
)}

private fun ComplexTail(fst: Double) : Complex = when(lexer.token) {
CALC_TOKENS.NUMBER -> {
val NUMBER = skip(CALC_TOKENS.NUMBER)
val CLOSESQUARE = skip(CALC_TOKENS.CLOSESQUARE)
Complex(fst, NUMBER.toDouble())
}
else -> throw Exception("expected token not found"
)}

fun parse() : Complex {
lexer.next()
val res = Expr()
if (lexer.token != CALC_TOKENS.EOF) {
throw Exception("EOF expected")
}
return res
}
}
class Complex(var re: Double, var im: Double) {
 companion object {
  fun parse(s: String): Complex {
   val splitted = s.split(",")
   if (splitted.size != 2) {
    throw Exception("Wrong complex number format.")
   }
   val a = splitted[0].filter { it != '('}.toDouble()
   val b = splitted[1].filter { it != ')'}.toDouble()
   return Complex(a, b)
  }
 }
}

fun plus(a: Complex, b: Complex): Complex {
 return Complex(a.re + b.re, a.im + b.im)
}

fun minus(a: Complex, b: Complex): Complex {
 return Complex(a.re - b.re, a.im - b.im)
}

fun mul(a: Complex, b: Complex): Complex {
 return Complex(a.re * b.re - a.im * b.im, a.re * b.im + b.re * a.im)
}

fun div(a: Complex, b: Complex): Complex {
 val denom = a.im * a.im + b.im * b.im
 return Complex((a.re * b.re + a.im * b.im) / denom, (a.im * b.re  - a.re * b.im) / denom)
}
