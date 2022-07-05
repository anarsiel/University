package com.company

import java.io.IOException
import java.io.Reader


const val UNKNOWN_CHAR = -2

open class BaseLexer(_reader: Reader,
                     literals: Map<Int, String>,
                     patterns: Map<Int, Regex>,
                     private val tokensToSkip: Set<Int>,
                     private val EOF_TOKEN: Int = -1) {

    private val allTokens: Map<Int, Regex> = literals.mapValues { (_, v) -> Regex.escape(v).toRegex() } + patterns + (UNKNOWN_CHAR to Regex(".+"))
    private val groupsToTokens: MutableMap<Int, Int> = LinkedHashMap()

    private val tokenStream: Iterator<TokenMatch>

    var token: Int = EOF_TOKEN
        private set

    var tokenValue: String? = null
        private set

    var position: Int = -1
        private set

    init {
        val text = _reader.readText()
        var grp = 0

        tokenStream = allTokens
                .map { (t, r) ->
                    groupsToTokens[grp++] = t
                    "($r)"
                }.joinToString("|")
                .toRegex()
                .findAll(text)
                .map {
                    it.groups.mapIndexedNotNull { i, g ->
                        if (i == 0 || g == null) null else TokenMatch(i, g.range.first, g.value)
                    }.singleOrNull() ?: throw Exception("Undefined tokens ${it.value}")
                }
                .iterator()
    }

    data class TokenMatch(val groupPos: Int, val strPos: Int, val value: String? = null)

    private fun _next() {
        if (!tokenStream.hasNext()) {
            if (token != EOF_TOKEN) {
                token = EOF_TOKEN
                tokenValue = null
                return
            } else throw IOException("No more tokens!")
        }
        val (g, s, v) = tokenStream.next()
        token = groupsToTokens.getValue(g - 1)
        position = s
        tokenValue = v
        if (token == UNKNOWN_CHAR) throw Exception("Unexpected symbol $v")
    }

    fun next() {
        do {
            _next()
        } while (token in tokensToSkip)
    }
}