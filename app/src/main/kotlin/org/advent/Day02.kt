package org.advent

import kotlin.math.abs


fun day02(input: String = "02.txt"): List<Long> =
    listOf(
        Util.readLines(input)
            .map { it.split(' ').map(String::toInt) }
            .filter(::isSafe)
            .count()
    )

private fun isSafe(it: List<Int>): Boolean {
    var ascending: Boolean? = null
    for (i in 0 until it.size - 1) {
        val first = it[i]
        val second = it[i + 1]
        if (abs(second - first) > 3) return false
        when {
            first == second -> return false
            first < second -> {
                if (false == ascending)
                    return false
                else ascending = true
            }

            first > second -> {
                if (true == ascending)
                    return false
                else ascending = false
            }
        }
    }
    return true
}