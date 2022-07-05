package com.company.calcTest

import com.company.CalcLexer
import com.company.CalcParser
import com.company.Complex
import java.io.File

fun main(args: Array<String>) {
    val lexer = CalcLexer(File("calc").bufferedReader())
    val parser = CalcParser(lexer)
    val res = parser.parse()
    print("${res.re}, ${res.im}")
}