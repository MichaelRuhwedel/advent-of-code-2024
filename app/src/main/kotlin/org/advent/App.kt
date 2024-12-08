package org.advent

fun main() {
    sequenceOf( day01(), day02(), day03(), day04(), day05()).flatten()
        .mapIndexed { i, result -> "Day ${pad(i / 2)} - Part ${i % 2 + 1}: $result" }
        .forEach(::println)
}

private fun pad(i: Int) = (i + 1)
    .toString()
    .padStart(1)
