package org.advent

fun main() {
    sequenceOf( day01(), day02() ).flatten()
        .mapIndexed { i, result -> "Day ${pad(i)}: $result" }
        .forEach(::println)
}

private fun pad(i: Int) = (i + 1)
    .toString()
    .padStart(2)
