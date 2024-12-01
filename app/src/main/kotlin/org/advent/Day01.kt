package org.advent

import kotlin.math.abs

val answer01: (Pair<List<Int>, List<Int>>) -> Int = { (ls, rs) ->
    ls.sorted().zip(rs.sorted())
    { l, r -> abs(l - r) }
        .reduce(Int::plus)
}

val answer02: (Pair<List<Int>, List<Int>>) -> Int = { (ls, rs) ->
    rs
        .fold(mutableMapOf<Int, Int>())
        { acc, i ->
            acc[i] = acc.getOrDefault(i, 0) + 1
            acc
        }
        .let {
            ls.map { l ->
                l * it.getOrDefault(l, 0)
            }
                .reduce(Int::plus)
        }
}