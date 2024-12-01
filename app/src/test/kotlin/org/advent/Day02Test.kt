package org.advent


import kotlin.test.Test
import kotlin.test.assertEquals

class Day02Test {
    @Test
    fun appHasAGreeting() {
        Util("01-test.txt")
            .coords
            .let(Day02()::answer)
            .let { it: Int -> assertEquals(31, it) }
    }
}