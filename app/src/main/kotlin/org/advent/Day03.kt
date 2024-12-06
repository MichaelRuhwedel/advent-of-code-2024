package org.advent


val mul = """mul\((\d+),(\d+)\)""".toRegex()
val ignore = """(don't\(\)|$mul|do\(\))""".toRegex()

fun day03(input: String = "03.txt", input2: String = "03.txt"): List<Int> {
    return listOf(
        Util.readText(input)
            .let(::mulSum1),
        Util.readText(input2)
            .let(::mulSum2)
    )
}

private fun mulSum1(text: String) = text
    .let(mul::findAll)
    .map(MatchResult::destructured)
    .map { (a, b) -> a.toInt() * b.toInt() }
    .sum()

private fun mulSum2(text: String) = text
    .let(ignore::findAll)
    .map(MatchResult::destructured)
    .fold(true to 0) { (doMul, sum), (all, a, b) ->
        when (all) {
            "don't()" -> false to sum
            "do()" -> true to sum
            else -> doMul to if (doMul) sum + a.toInt() * b.toInt() else sum
        }
    }.second
