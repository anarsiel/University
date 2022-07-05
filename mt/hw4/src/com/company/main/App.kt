package com.company.main

import com.company.GrammarLexer
import com.company.GrammarParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker
import java.nio.file.Files
import java.nio.file.Paths

fun main() {
    val input = readLine()
//    val input = "calc.gramma"
//    val input = "for.gramma"
//    val output = "src/com/company/calcTest/"
//    val output = "src/com/company/forTest/"

    val grammarName = input!!.removeSuffix(".gramma").capitalize()
    val output = "src/com/company/" + grammarName + "Test/"
    val collector = MyGrammarListener()
    val l = GrammarLexer(CharStreams.fromFileName(input))
    val p = GrammarParser(CommonTokenStream(l))
    val walker = ParseTreeWalker()
    walker.walk(collector, p.file())
    val lexer = LexerGrammarFilesGenerator(
            collector
    )

    val outLexer = Paths.get(output, grammarName + "Lexer.kt")
    outLexer.toFile().parentFile.mkdirs()
    Files.newBufferedWriter(outLexer).use { wr ->
        wr.write(lexer.generate(grammarName))
    }

    val parser = ParserGrammarFilesGenerator(
            collector
    )

    val outParser = Paths.get(output, grammarName + "Parser.kt")
    outLexer.toFile().parentFile.mkdirs()
    Files.newBufferedWriter(outParser).use { wr ->
        wr.write(parser.generate(grammarName))
    }
}