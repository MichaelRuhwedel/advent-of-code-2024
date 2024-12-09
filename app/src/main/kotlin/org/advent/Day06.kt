package org.advent

import org.advent.Guard.*

enum class Guard(val c: Char) {
    NORTH('^'), SOUTH('v'), WEST('<'), EAST('>')
}

fun day06(input: String = "06.txt"): List<Int> {
    val map = Util.readMatrix(input)

    var guard = map.findGuard()
    val visited = mutableSetOf<Pair<Int, Int>>()
    while (map.inMap(guard.first)) {
        guard = guard.let { (currentPos, direction: Guard) ->
            visited += currentPos
            currentPos
                .let { (i, j) ->
                    when (direction) {
                        NORTH -> goOrTurn(map, i - 1 to j, currentPos, EAST, direction)
                        SOUTH -> goOrTurn(map, i + 1 to j, currentPos, WEST, direction)
                        WEST -> goOrTurn(map, i to j - 1, currentPos, NORTH, direction)
                        EAST -> goOrTurn(map, i to j + 1, currentPos, SOUTH, direction)
                    }
                }
        }
//        map.forEachIndexed { i, row ->
//            row.forEachIndexed { j, c ->
//                if (i to j == guardPos.first) {
//                    print(guardPos.second.c)
//                } else
//                    print(c)
//            }
//            println()
//        }
//        println("---------------------------------")
    }
    return listOf(visited.size)
}

private fun goOrTurn(
    map: Array<CharArray>,
    nextPos: Pair<Int, Int>,
    currentPos: Pair<Int, Int>,
    nextDirection: Guard,
    oldDirection: Guard
) = if (map.isWall(nextPos)) {
    currentPos to nextDirection
} else {
    nextPos to oldDirection
}

private fun Array<CharArray>.isWall(ij: Pair<Int, Int>) =
    ij.let { (i, j) -> inMap(ij) && this[i][j] == '#' }

fun Array<CharArray>.inMap(guardPos: Pair<Int, Int>): Boolean = guardPos
    .let { (i, j) -> i in indices && j in this[i].indices }

private fun Array<CharArray>.findGuard(): Pair<Pair<Int, Int>, Guard> {
    forEachIndexed { i, row ->
        row.forEachIndexed { j, c ->
            Guard.entries
                .find { it.c == c }
                ?.let { guardDirection ->
                    return (i to j) to guardDirection
                }
        }
    }
    return -1 to -1 to NORTH
}