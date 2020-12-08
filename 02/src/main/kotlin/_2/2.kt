package _2

import getLinesFromInput

fun main() {
    val input = getLinesFromInput()
    println(input.filter { isLineValid(it) }.size)
}

fun isLineValid(line: String): Boolean {
    val splitted: List<String> = line.split(":")
    val password = splitted[1].trim()
    val rule = splitted[0].trim().split(" ")
    val charFromRule = rule[1][0]
    val counts = rule[0].split("-")
    val position1 = counts[0].toInt()
    val position2 = counts[1].toInt()
    val twoChars = getCharOrEmpty(position1, password) + getCharOrEmpty(position2, password)
    return twoChars.count { it == charFromRule } == 1
}

fun getCharOrEmpty(position: Int, password: String): String {
    if (password.length >= position) {
        return password[position -1].toString()
    }
    return ""
}