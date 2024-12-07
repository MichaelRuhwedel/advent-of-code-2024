package org.advent

val xmas = listOf('X', 'M', 'A', 'S')
var xmasVariations = setOf(xmas, xmas.reversed())

val mas = listOf('M', 'A', 'S')
val masVariations = setOf(mas, mas.reversed())

val wordLength = xmas.size
val wls = wordLength - 1
const val red = "\u001b[31m-\u001B[0m"

fun day04(input: String = "04.txt"): List<Int> {

    val chars = Util.readMatrix(input)
    return listOf(answer01(chars), answer02(chars))
}

private fun answer01(chars: Array<CharArray>): Int {
    var count = 0
    chars.forEachIndexed { i, row ->
        row.forEachIndexed { j, c ->
            var mark = false
            if (c == xmas.first() || c == xmas.last()) {
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
            if (c == 'A' && chars.isMas(i, j)) {
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
    i + wls < size && j + wls < this[i].size &&
            (0..wls)
                .map { this[i + it][j + it] }
                .toList()
                .let(xmasVariations::contains)

private fun Array<CharArray>.isMas(i: Int, j: Int) =
    i + 1 < size && j + 1 < this[i].size && 0 <= i - 1 && 0 <= j - 1 &&
            listOf(this[i - 1][j - 1], this[i][j], this[i + 1][j + 1]) in masVariations &&
            listOf(this[i - 1][j + 1], this[i][j], this[i + 1][j - 1]) in masVariations

private fun Array<CharArray>.isDiagonalL(i: Int, j: Int) =
    i + wls < size && 0 <= j - wls &&
            (0..wls)
                .map { this[i + it][j - it] }
                .toList()
                .let(xmasVariations::contains)

private fun Array<CharArray>.isVertical(i: Int, j: Int) =
    i + wls < size && (i..<i + xmas.size).map { this[it][j] }.toList() in xmasVariations

private fun Array<CharArray>.isHorizontal(j: Int, i: Int) =
    j + wordLength <= this[i].size &&
            this[i].slice(j..<j + xmas.size) in xmasVariations