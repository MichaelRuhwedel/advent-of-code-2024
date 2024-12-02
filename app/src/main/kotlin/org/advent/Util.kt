package org.advent

class Util(fileName: String) {
    private val splitter = "\\s+".toRegex()
    private val left = mutableListOf<Int>()
    private val right = mutableListOf<Int>()

    init {
        (this::class.java.getResourceAsStream("/$fileName") ?: throw Exception("Could not load file"))
            .bufferedReader()
            .lines()
            .map { line -> line.split(splitter) }
            .forEach { (l, r) ->
                left += l.toInt()
                right += r.toInt()
            }

    }

    val coords = Pair(left.asSequence(), right.asSequence())
}