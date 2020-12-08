package _1

import getLinesFromInput

fun main() {
    val input = getLinesFromInput()
    var treeCounter = 0
    for (i in input.indices) {
        val line = input[i]
        val positionInLine = i * 3 % line.length
        if (line[positionInLine] == '#') {
            treeCounter++
        }
    }
    println(treeCounter)
}
