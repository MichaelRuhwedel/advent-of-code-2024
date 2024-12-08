package org.advent

internal val xmas = listOf('X', 'M', 'A', 'S')
internal val xmasVariations = setOf(xmas, xmas.reversed())

private val mas = listOf('M', 'A', 'S')
private val masVariations = setOf(mas, mas.reversed())

private val wordLength = xmas.size
private val continuation = wordLength - 1
private const val red = "\u001b[31m-\u001B[0m"

fun day04(input: String = "04.txt"): List<Int> {

    val chars = Util.readMatrix(input)
    return listOf(answer01(chars), answer02(chars))
}

private fun answer01(chars: Array<CharArray>): Int {
    var count = 0
    chars.forEachIndexed { i, row ->
        row.forEachIndexed { j, c ->
            var mark = false
            if (c in setOf(xmas.first(), xmas.last())) {
                if (chars.isHorizontal(j, i)) {
                    mark = true
                    count++
                }
                if (chars.isVertical(i, j)) {
                    mark = true
                    count++
                }
                if (chars.isDiagonalR(i, j)) {
                    mark = true
                    count++
                }
                if (chars.isDiagonalL(i, j)) {
                    mark = true
                    count++
                }
            }
            print(if (mark) red else c)
        }
        println()
    }
    return count
}

private fun answer02(chars: Array<CharArray>): Int {
    var count = 0
    chars.forEachIndexed { i, row ->
        row.forEachIndexed { j, c ->
            if (c == mas[1] && chars.isMas(i, j)) {
                print(red)
                count++
            } else {
                print(c)
            }
        }
        println()
    }
    return count
}


private fun Array<CharArray>.isDiagonalR(i: Int, j: Int) =
    i + continuation < size && j + continuation < this[i].size &&
            (0..continuation)
                .map { this[i + it][j + it] }
                .toList()
                .let(xmasVariations::contains)

private fun Array<CharArray>.isMas(i: Int, j: Int) =
    i + 1 < size && j + 1 < this[i].size && 0 <= i - 1 && 0 <= j - 1 &&
            listOf(this[i - 1][j - 1], this[i][j], this[i + 1][j + 1]) in masVariations &&
            listOf(this[i - 1][j + 1], this[i][j], this[i + 1][j - 1]) in masVariations

private fun Array<CharArray>.isDiagonalL(i: Int, j: Int) =
    i + continuation < size && 0 <= j - continuation &&
            (0..continuation)
                .map { this[i + it][j - it] }
                .toList()
                .let(xmasVariations::contains)

private fun Array<CharArray>.isVertical(i: Int, j: Int) =
    i + continuation < size && (i..<i + xmas.size).map { this[it][j] }.toList() in xmasVariations

private fun Array<CharArray>.isHorizontal(j: Int, i: Int) =
    j + wordLength <= this[i].size &&
            this[i].slice(j..<j + xmas.size) in xmasVariations