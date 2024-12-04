package org.advent

import java.util.stream.Stream

object Util {
    @JvmStatic
    fun readLines(fileName: String): Stream<String> = fileName.let(::reader).lines()

    @JvmStatic
    fun readText(fileName: String) = fileName.let(::reader).readText()

    private fun reader(fileName: String) =
        this::class.java
            .getResourceAsStream("/$fileName")
            ?.bufferedReader()
            ?: throw Exception("Could not load file")
}