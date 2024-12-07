package org.advent

import java.io.InputStream
import java.util.stream.Stream

object Util {
    @JvmStatic
    fun readLines(fileName: String): Stream<String> = fileName.let(::reader).lines()

    @JvmStatic
    fun readText(fileName: String) = fileName.let(::reader).readText()
    fun readMatrix(fileName: String): Array<CharArray> = fileName
    .let(::readLines).toList()
    .map(String::toCharArray).toTypedArray()

    private fun reader(fileName: String) =
        inputStream(fileName)
            .bufferedReader()

    private fun inputStream(fileName: String): InputStream = this::class.java
        .getResourceAsStream("/$fileName") ?: throw Exception("Could not load file")
}