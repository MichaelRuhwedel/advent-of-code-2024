package org.advent

fun day05(input: String = "05.txt"): List<Int> {
    val (rules, updates) = read(input)
    val sum = updates
        .filter { update -> isSafe(update, rules) }
        .onEach { println(it) }
        .sumOf { update -> update[update.size / 2] }
    return listOf(sum)
}

private fun read(input: String): Pair<MultiMap<Int, Int>, MutableList<List<Int>>> {
    val rules = MultiMap<Int, Int>()
    val updates = mutableListOf<List<Int>>()
    Util.readLines(input)
        .forEach { l ->
            when {
                '|' in l ->
                    l.split('|')
                        .let { (a, b) ->
                            rules.put(a.toInt(), b.toInt())
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
        rules[page]
            .let { pagesRequiredBefore ->
                pagesRequiredBefore.all { pageRequiredBefore ->
                    val indexOf = update.indexOf(pageRequiredBefore)
                    (indexOf == -1 || (currentIndex < indexOf))
                }
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

    operator fun get(key: K): Collection<V> {
        return map[key] ?: hashSetOf()
    }

    fun put(key: K, value: V) {
        if (key !in map) {
            map[key] = HashSet()
        }
        map[key]!!.add(value)
    }
}
