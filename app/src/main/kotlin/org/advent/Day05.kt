package org.advent

fun day05(input: String = "05.txt"): List<Int> {
    val (rules, updates) = read(input)
    val sum = updates
        .filter { update -> isSafe(update, rules) }
        .sumOf { update -> update[update.size / 2] }
    val sum2 = updates
        .filter { update -> !isSafe(update, rules) }
        .map {
            it.sortedWith { a, b ->
                when {
                    b in rules[a] -> -1
                    else -> 0
                }
            }
        }
        .sumOf { update -> update[update.size / 2] }
    return listOf(sum, sum2)
}

private fun read(input: String): Pair<MultiMap<Int, Int>, MutableList<List<Int>>> {
    val rules = MultiMap<Int, Int>()
    val updates = mutableListOf<List<Int>>()
    Util.readLines(input)
        .forEach { l ->
            when {
                '|' in l ->
                    l.split('|')
                        .let { (page, comesAfter) ->
                            rules[page.toInt()] += comesAfter.toInt()
                        }

                ',' in l ->
                    updates += l.split(',')
                        .map(String::toInt)
            }
        }
    return rules to updates
}

private fun isSafe(update: List<Int>, rules: MultiMap<Int, Int>) = update
    .withIndex()
    .all { (currentIndex, page) ->
        rules[page].all { pageRequiredAfter ->
            val indexOf = update.indexOf(pageRequiredAfter)
            (indexOf == -1 || (currentIndex < indexOf))
        }
    }

private fun answer01(): String {
    return ""
}

private fun answer02(): String {
    return ""
}

class MultiMap<K, V> {
    private val map: MutableMap<K, MutableCollection<V>> = HashMap()
    operator fun get(key: K): MutableCollection<V> {
        return map.getOrPut(key, ::hashSetOf)
    }
}