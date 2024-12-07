package org.advent

val xmas = listOf('X', 'M', 'A', 'S')
var variations = setOf(xmas, xmas.reversed())
val wordLength = xmas.size
val wls = wordLength - 1
const val red = "\u001b[31m"

// Resets previous color codes
const val reset = "\u001b[0m"

fun day04(input: String = "04.txt"): List<Int> {

    val chars = Util.readMatrix(input)
    var count = 0
    chars.forEachIndexed { i, row ->
        row.forEachIndexed { j, c ->
            var mark = false
            if (c == xmas.first() || c == xmas.last()) {
                if (chars.isHorizontal(j, i)) {
                    mark = true
                    count++;
                }
                if (chars.isVertical(i, j)) {
                    mark = true
                    count++
                }
                if (chars.isDiagonal(i, j)) {
                    mark = true
                    count++
                }
                if (chars.isDiagonalL(i, j)) {
                    mark = true
                    count++
                }
            }
            print(if (mark) "$red-$reset" else c)
        }
        println()
    }
    return listOf(count)
}


private fun Array<CharArray>.isDiagonal(i: Int, j: Int) = i + wls < size && j + wls < this[i].size &&
        (0..wls)
            .map { this[i + it][j + it] }
            .toList()
            .let(variations::contains)

private fun Array<CharArray>.isDiagonalL(
    i: Int,
    j: Int
) = i + wls < size && 0 <= j - wls &&
        (0..wls)
            .map { this[i + it][j - it] }
            .toList()
            .let(variations::contains)

private fun Array<CharArray>.isVertical(i: Int, j: Int) =
    i + wls < size && (i..<i + xmas.size).map { this[it][j] }.toList() in variations

private fun Array<CharArray>.isHorizontal(j: Int, i: Int) =
    j  <= this[i].size - wordLength &&
            this[i].slice(j..<j + xmas.size) in variations