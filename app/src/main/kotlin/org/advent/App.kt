package org.advent

fun main() {
    day01()
        .mapIndexed { i, result -> "Day ${pad(i)}: $result" }
        .forEach(::println)
}

private fun pad(i: Int) = (i + 1)
    .toString()
    .padStart(2)
