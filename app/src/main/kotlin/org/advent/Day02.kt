package org.advent

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