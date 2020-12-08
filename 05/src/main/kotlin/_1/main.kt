package _1

import getLinesFromInput


fun main() {
    val lines: List<String> = getLinesFromInput()
    print(lines.map { transform(it) }.maxOrNull())
}

fun transform(it: String): Int {
    val row = it.substring(0, 7).replace("F", "0").replace("B", "1")


    val column = it.substring(7, 10).replace("L" , "0").replace("R", "1")

    return Integer.parseInt(row, 2) * 8 + Integer.parseInt(column, 2)
}
