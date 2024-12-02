package org.advent

import java.util.stream.Stream

object Util {



    @JvmStatic
    fun readLines(fileName: String): Stream<String> =
        (this::class.java.getResourceAsStream("/$fileName") ?: throw Exception("Could not load file"))
            .bufferedReader()
            .lines()
}