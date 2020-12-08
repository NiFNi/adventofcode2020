package _1

import getLinesFromInputWithBlank


fun main() {
    val lines: MutableList<String> = getLinesFromInputWithBlank().toMutableList()
    println(split(lines, listOf(), ""))
}


fun split(lines: MutableList<String>, result: List<String>, currentString: String): List<String> {
    if (lines.isEmpty()) {
        return result
    }
    val line = lines.removeFirst()
    return when (line) {
        "" -> split(lines, result.toMutableList() + currentString, "")
        else -> split(lines, result, currentString + line)
    }
}