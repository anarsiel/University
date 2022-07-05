package com.company.main

import com.company.escape
import java.lang.StringBuilder
import java.util.LinkedHashMap
import java.util.LinkedHashSet
import java.util.regex.Pattern


class LexerGrammarFilesGenerator(private val collector: MyGrammarListener) {

    private val skipTokens: LinkedHashSet<Int?>
        get() = collector.skippedTokens

    private val literals: LinkedHashMap<Int?, String>
        get() = collector.literals

    private val patterns: LinkedHashMap<Int?, Pattern>
        get() = collector.patternHashMap

    private val tokenTable: Map<String, Int>
        get() = collector.tokenTable

    fun generate(grammarName: String) : String {
        var s = StringBuilder()
        val enumName = "${grammarName.toUpperCase()}_TOKENS"
        s.append(collector.pckg!!).append("\n")
        s.append("import java.io.Reader\n")
        s.append("private val stringTerm: Map<Int, String> = mapOf(")
        for ((t, v) in literals) {
            s.append("$t to \"${v.escape()}\",")
        }
        s = s.deleteCharAt(s.length - 1)
        s.append(")\n\n")
        s.append("private val RegexTerm: Map<Int, Regex> = mapOf(\n")
        patterns.forEach { (t, r) -> s.append("$t to Regex(\"${r.toString().escape()}\"),") }
        s = s.deleteCharAt(s.length - 1)
        s.append(")\n")
        s.append("private val skipTokens = setOf(${skipTokens.joinToString()})\n\n")
        s.append("object $enumName {\n")
        tokenTable.forEach { (t, i) -> s.append("val $t = $i\n") }
        s.append("}\n\n")
        s.append("class ${grammarName.capitalize()}Lexer(reader: Reader)\n")
        s.append(": BaseLexer(reader, stringTerm, RegexTerm, skipTokens, $enumName.EOF)\n")
        return s.toString()
    }
}