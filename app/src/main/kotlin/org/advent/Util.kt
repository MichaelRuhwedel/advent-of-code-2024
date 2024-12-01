package org.advent

class Util {
    private val splitter = "\\s+".toRegex()
    private val left = mutableListOf<Int>()
    private val right = mutableListOf<Int>()

    init {
        (this::class.java.getResourceAsStream("/01.txt") ?: throw Exception("Could not load file"))
            .bufferedReader()
            .useLines { lines ->
                lines.map { line -> line.split(splitter) }
                    .forEach { coords ->
                        left += coords[0].toInt()
                        right += coords[1].toInt()
                    }
            }

        left.sort()
        right.sort()
    }

    fun coords(): Pair<List<Int>, List<Int>> = Pair(left, right)
}