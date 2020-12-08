package _2

import getLinesFromInput

fun main() {
    val input = getLinesFromInput()
    val steps = listOf(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2))
    println(steps.map { countTrees(it.first, it.second, input) }.reduce(Long::times))
}


fun countTrees(stepsRight: Int, stepsDown: Int, input: List<String>): Long {
    var treeCounter: Long = 0
    for (i in input.indices step stepsDown) {
        val line = input[i]
        val positionInLine = (i / stepsDown) * stepsRight % line.length
        if (line[positionInLine] == '#') {
            treeCounter++
        }
    }
    return treeCounter
}