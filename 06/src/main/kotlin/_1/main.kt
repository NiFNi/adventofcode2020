package _1

import getLinesFromInputWithBlank


fun main() {
    val lines: MutableList<String> = getLinesFromInputWithBlank().toMutableList()
    print(split(lines, listOf(), "").map { it.toCharArray().toSet() }.map { it.size }
        .reduce(Int::plus))
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