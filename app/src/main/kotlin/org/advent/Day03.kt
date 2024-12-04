package org.advent


val mul = """mul\((\d+),(\d+)\)""".toRegex()
val ignore = """(don't\(\)|${mul}|do\(\))""".toRegex()

fun day03(input: String = "03.txt", input2: String = "03.txt"): List<Int> {
    return listOf(
        Util.readText(input)
            .let(::mulSum1),
        Util.readText(input2)
            .let(::mulSum2)
    )
}

var doMul = true

private fun mulSum1(text: String) = text
    .let(mul::findAll)
    .map { it.destructured }
    .map { (a, b) -> a.toInt() * b.toInt() }
    .sum()

private fun mulSum2(text: String) = text
    .let(ignore::findAll)
    .map { it.destructured }
    .onEach { (all, _, _) ->
        when (all) {
            "don't()" -> doMul = false
            "do()" -> doMul = true
        }
    }
    .filter { (_, a, _) -> doMul && a.isNotEmpty() }
    .map { (_, a, b) -> a.toInt() * b.toInt() }
    .sum()