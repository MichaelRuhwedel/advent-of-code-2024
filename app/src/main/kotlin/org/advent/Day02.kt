package org.advent

import kotlin.math.abs


fun day02(input: String = "02.txt"): List<Int> {
    val lines = Util.readLines(input)
        .map { it.split(' ').map(String::toInt) }
        .toList()
    return listOf(
        lines.count(::isSafe),
        lines.count { levels ->
            isSafe(levels) ||
                    (0..levels.size)
                        .any { isSafe(levels.filterIndexed { index, _ -> index != it }) }
        }

    )
}

private fun isSafe(list: List<Int>): Boolean {
    var ascending: Boolean? = null
    return list
        .windowed(2)
        .all { (current, next) ->
            if (abs(next - current) !in 1..3)
                return false

            when {
                current < next ->
                    if (ascending == false)
                        return false
                    else
                        ascending = true

                next < current ->
                    if (ascending == true)
                        return false
                    else
                        ascending = false
            }
            true
        }
}