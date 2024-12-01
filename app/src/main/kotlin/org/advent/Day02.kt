package org.advent

import kotlin.math.abs

class Day02 {
    fun answer(coords: Pair<List<Int>, List<Int>>): Unit {
        coords.first.zip(coords.second)
        { l, r -> abs(l - r) }
            .reduce { acc, i -> acc + i }
            .let(::println)
    }
}