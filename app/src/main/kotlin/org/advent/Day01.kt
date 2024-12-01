package org.advent

import kotlin.math.abs

val answer01: (Pair<List<Int>, List<Int>>) -> Int = { (ls, rs) ->
    ls.sorted().zip(rs.sorted())
    { l, r -> abs(l - r) }
        .reduce(Int::plus)
}