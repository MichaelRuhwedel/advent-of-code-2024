package org.advent

fun main() {
    val cs = Util("01.txt").coords

    listOf(
        answer01,
        answer02
    )
        .map { it(cs) }
        .mapIndexed { i, result -> "Day ${pad(i)}: $result" }
        .forEach(::println)
}

private fun pad(i: Int) = (i + 1)
    .toString()
    .padStart(2)
