package org.advent

import kotlin.math.abs


fun day01(): Sequence<Int> {
    val splitter = "\\s+".toRegex()
    val left = mutableListOf<Int>()
    val right = mutableListOf<Int>()

    Util.readLines("01.txt")
        .map { it.split(splitter) }
        .forEach { (l, r) ->
            left += l.toInt()
            right += r.toInt()
        }

    return sequenceOf(answer01, answer02)
        .map { it(left, right) }
}

val answer01: (Collection<Int>, Collection<Int>) -> Int = { ls, rs ->
    ls.sorted().zip(rs.sorted())
    { l, r -> abs(l - r) }
        .reduce(Int::plus)
}

val answer02: (Collection<Int>, Collection<Int>) -> Int = { ls, rs ->
    rs
        .groupingBy { it }.eachCount()
        .let { rightValueToCount ->
            ls.map { (rightValueToCount[it] ?: 0) * it }
                .reduce(Int::plus)
        }
}