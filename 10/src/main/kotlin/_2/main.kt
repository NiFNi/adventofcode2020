package _2

import getLinesFromInput

fun main() {
    val lines = getLinesFromInput().map { it.toInt() }.sorted()
    var oneJolt = 0
    var threeJolt = 0
    var nextAdapter = loadNext(0, lines)
    when (nextAdapter) {
        1 -> oneJolt++
        3 -> threeJolt++
    }
    val adapters = mutableListOf<Int>()

    while (nextAdapter != null) {
        adapters.add(nextAdapter)
        val next = loadNext(nextAdapter, lines)
        when (next?.let { it - nextAdapter!! }) {
            1 -> oneJolt++
            3 -> threeJolt++
        }
        nextAdapter = next
    }
    threeJolt++
    println(oneJolt)
    println(threeJolt)
    println(oneJolt * threeJolt)
}

fun loadNext(i: Int, lines: List<Int>): Int? {
    return lines.find { it == i + 1 } ?: lines.find { it == i + 2 } ?: lines.find { it == i + 3 }
}

