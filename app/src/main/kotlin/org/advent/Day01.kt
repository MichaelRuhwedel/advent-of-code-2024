package org.advent

import kotlin.math.abs

val answer01: (Pair<Sequence<Int>, Sequence<Int>>) -> Int = { (ls, rs) ->
    ls.sorted().zip(rs.sorted())
    { l, r -> abs(l - r) }
        .reduce(Int::plus)
}

val answer02: (Pair<Sequence<Int>, Sequence<Int>>) -> Int = { (ls, rs) ->
    rs
        .groupingBy { it }.eachCount()
        .let { rightValueToCount ->
            ls.map { (rightValueToCount[it] ?: 0) * it }
                .reduce(Int::plus)
        }
}