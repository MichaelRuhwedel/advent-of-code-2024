package org.advent

import kotlin.math.abs

class Day01 {
    private val splitter = "\\s+".toRegex()

    init {
        val left = mutableListOf<Int>()
        val right = mutableListOf<Int>()

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

        left
            .mapIndexed { index, lValue -> abs(lValue - right[index]) }
            .reduce { acc, i -> acc + i }
            .let(::println)
    }
}