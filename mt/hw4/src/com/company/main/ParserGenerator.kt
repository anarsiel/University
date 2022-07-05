package com.company.main

import com.company.*
import java.lang.StringBuilder


class ParserGrammarFilesGenerator(private val collector: MyGrammarListener) {

    private val first: MutableMap<String, MutableSet<String>> = mutableMapOf()
    private val follow: MutableMap<String, MutableSet<String>> = mutableMapOf()

    init {
        // generate FIRST for all rules and terms
        collector.terms.forEach {
            first[it] = mutableSetOf(it)
        }
        collector.rules.forEach { (name, rule) ->
            first[name] = mutableSetOf()
            if (rule.ways.any { it.prods[0].name == collector.EPS }) first[name]!!.add(
                    collector.EPS
            )
        }

        var changed = true
        while (changed) {
            changed = false
            for ((name, rule) in collector.rules) {
                for (prod in rule.ways) {
                    for (i in prod.prods.indices) {
                        // for rules A -> aB, and EPS in FIRST(a), add to FIRST(A) FIRST(B)
                        val cur = prod.prods[i].name
                        changed = changed || first.getValue(name).addAll(first.getValue(cur))
                        if (collector.EPS in first.getValue(cur)) {
                            // add EPS to A, if all prods has EPS
                            if (i == prod.prods.size - 1) changed = changed || first.getValue(name).add(collector.EPS)
                        } else {
                            break
                        }
                    }
                }
            }
        }


        // generate FOLLOW for all rules and terms

        collector.nonTerminals.forEach { follow[it] = mutableSetOf() }
        follow.getValue(collector.startRule!!).add(collector.EOF)

        changed = true
        while (changed) {
            changed = false
            for ((name, rule) in collector.rules) {
                for (prod in rule.ways) {
                    // For A -> aBb, add to FOLLOW(B) all from FIRST(b) except EPS
                    (0..prod.prods.size - 2)
                            .filter { prod.prods[it] is NonTerm }
                            .forEach { i ->
                                changed = changed || follow.getValue(prod.prods[i].name).addAll(
                                        first.getValue(prod.prods[i + 1].name).filter { it != collector.EPS }
                                )
                            }

                    // For A -> aB, add to FOLLOW(B) all from FOLLOW(A)
                    if (prod.prods.last() is NonTerm)
                        changed = changed || follow.getValue(prod.prods.last().name).addAll(follow.getValue(name))

                    // For A -> aBb, FIRST(b) has EPS, add to FOLLOW(B) all from FOLLOW(A)
                    if (prod.prods.size > 1 && collector.EPS in first.getValue(prod.prods.last().name)) {
                        if (prod.prods[prod.prods.size - 2] is NonTerm)
                            changed = changed || follow.getValue(prod.prods[prod.prods.size - 2].name).addAll(follow.getValue(name))
                    }
                }
            }
        }
    }

    fun generate(grammarName: String): String {
        val s = StringBuilder()
        val gramName = grammarName.capitalize()
        val enumName = "${grammarName.toUpperCase()}_TOKENS"
        s.append(collector.pckg!!)
        s.append("\n")
        s.append("@Suppress(\"UNUSED_VARIABLE\")\n")
        s.append("class ${gramName}Parser(private val lexer: ${gramName}Lexer) {\n\n")
        s.append("private fun skip(token: Int): String {\n")
        s.append("if (lexer.token != token) throw Exception(\"Expected token not found:(\")\n");
        s.append("val res = lexer.tokenValue ?: throw IllegalArgumentException(\"Cannot skip EOF token\")\n")
        s.append("lexer.next()\n")
        s.append("return res\n")
        s.append("}\n\n")
        for ((name, rule) in collector.rules) {
            s.append(
                    "private fun ${name.capitalize()}(${rule.args?.joinToString { a -> "${a.name}: ${a.type}" }.orEmpty()}) : ${
                    rule.returnType
                            ?: "Unit"
                    } = when(lexer.token) {\n"
            )
            val m = mapRules(name, rule)
            for ((prod, tokens) in m) {
                // Tokens
                s.append("${tokens.joinToString { "$enumName.$it" }} -> {\n")
                // Declarations
                val repeatedProds = prod.prods.groupingBy { it.name }.eachCount().filterValues { i -> i > 1 }
                repeatedProds.forEach { (e, _) ->
                    when (e) {
                        in collector.terms -> s.append("val $e : MutableList<String> = mutableListOf()\n")
                        in collector.nonTerminals -> {
                            val returnType = collector.rules[e]!!.returnType
                            if (returnType != null)
                                s.append("val $e : MutableList<$returnType> = mutableListOf()\n")
                        }
                    }
                }

                // Assignments
                for (elem in prod.prodsOrCode) {
                    when (elem) {
                        is Term -> {
                            if (elem.name == collector.EPS) continue
                            if (elem.name in repeatedProds)
                                s.append("${elem.name}.add(skip($enumName.${elem.name}))\n")
                            else
                                s.append("val ${elem.name} = skip($enumName.${elem.name})\n")
                        }
                        is NonTerm -> {
                            val callAttrs = elem.callArgs?.joinToString().orEmpty()
                            if (elem.name in repeatedProds)
                                s.append("${elem.name}.add(${elem.name.capitalize()}($callAttrs))\n")
                            else
                                s.append("val ${elem.name} = ${elem.name.capitalize()}($callAttrs)\n")
                        }
                        is Code -> s.append(elem.code + "\n")
                    }
                }
                s.append("}\n")
            }

            // else (error)
            s.append("else -> throw Exception(\"expected token not found\"\n)")
            s.append("}\n\n")
        }
        val startRule = collector.rules.getValue(collector.startRule!!)

        s.append("fun parse(${startRule.args?.joinToString { a -> "${a.name}: ${a.type}" }.orEmpty()}) : ${startRule.returnType ?: "Unit"} {\n")
        s.append("lexer.next()\n")
        s.append("val res = ${collector.startRule!!.capitalize()}(${startRule.args?.joinToString { a -> a.name }.orEmpty()})\n")
        s.append("if (lexer.token != $enumName.EOF) {\n")
        s.append("throw Exception(\"EOF expected\")\n")
        s.append("}\n")
        s.append("return res\n")
        s.append("}\n")
        s.append("}\n")

        s.append(
                """
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

                """.trimIndent()
        )
        return s.toString()
    }

    // for rule A -> aAb | cBd, for each way generate
    private fun mapRules(name: String, rule: Rule): Map<Way, List<String>> {
        return rule.ways
                .associate { prod ->
                    if (prod.prods[0].name == collector.EPS) {
                        prod to follow.getValue(name).toList()
                    } else {
                        prod to first.getValue(prod.prods[0].name).toList()
                    }
                }
    }
}