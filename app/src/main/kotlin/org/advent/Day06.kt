package org.advent

import org.advent.Guard.*

enum class Guard(val c: Char) {
    NORTH('^'), SOUTH('v'), WEST('<'), EAST('>')
}

fun day06(input: String = "06.txt"): List<Int> {
    val map = Util.readMatrix(input)

    var guardPos = map.findGuard()
    println("Guard at ${guardPos}")
    val visited = mutableSetOf(guardPos.first)
    while (map.inMap(guardPos.first)) {
        val gIJ = guardPos.first
        val direction = guardPos.second
        gIJ.let { (i, j) ->
            visited += gIJ
            guardPos = when (direction) {
                NORTH -> {
                    if (map.isWall(i - 1, j)) {
                        gIJ to EAST
                    } else {
                        (i - 1 to j) to NORTH
                    }
                }

                SOUTH -> {
                    if (map.isWall(i + 1, j)) {
                        gIJ to WEST
                    } else {
                        (i + 1 to j) to SOUTH
                    }
                }


                WEST -> {
                    if (map.isWall(i, j - 1)) {
                        gIJ to NORTH
                    } else {
                        (i to j - 1) to WEST
                    }
                }

                EAST -> {
                    if (map.isWall(i, j + 1)) {
                        gIJ to SOUTH
                    } else {
                        (i to j + 1) to EAST
                    }
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

private fun Array<CharArray>.isWall(i: Int, j: Int) = i in this.indices && j in this[i].indices && this[i][j] == '#'

fun Array<CharArray>.inMap(guardPos: Pair<Int, Int>): Boolean = guardPos
    .let { (i, j) ->
        i in indices && j in this[i].indices
    }

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