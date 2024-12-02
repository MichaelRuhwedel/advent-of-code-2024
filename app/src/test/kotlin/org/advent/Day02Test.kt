package org.advent


import kotlin.test.Test
import kotlin.test.assertEquals

class Day02Test {
    @Test
    fun appHasAGreeting() {
        Util("01-test.txt")
            .coords
            .let(answer02)
            .let { it: Int -> assertEquals(31, it) }
    }
}