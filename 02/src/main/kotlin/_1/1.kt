package _1

import getLinesFromInput

fun main() {


    val input = getLinesFromInput()
    println(
        input.filter
        { isLineValid(it) }.size
    )
}

fun isLineValid(line: String): Boolean {
    val splitted: List<String> = line.split(":")
    val password = splitted[1].trim()
    val rule = splitted[0].trim().split(" ")
    val charFromRule = rule[1][0]
    val counts = rule[0].split("-")
    val min = counts[0].toInt()
    val max = counts[1].toInt()
    val countOfNeededChar = password.count { it == charFromRule }
    return countOfNeededChar in min..max
}