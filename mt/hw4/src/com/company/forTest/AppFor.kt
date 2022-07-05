package com.company.forTest

import com.company.ForLexer
import com.company.ForParser
import java.io.File

fun main(args: Array<String>) {
    val lexer = ForLexer(File("for").bufferedReader())
    val parser = ForParser(lexer)
    parser.parse()
    println("OK")
}