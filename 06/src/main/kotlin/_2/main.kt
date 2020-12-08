package _2

import getLinesFromInput


fun main() {
    val lines: List<String> = getLinesFromInput()
    val sorted = lines.map { transform(it) }.sorted()
    val first = sorted.first()
    val last = sorted.last()
    for (i in first..last) {
        if (!sorted.contains(i)) {
            println(i)
        }
    }
}

fun transform(it: String): Int {
    val row = it.substring(0, 7).replace("F", "0").replace("B", "1")


    val column = it.substring(7, 10).replace("L", "0").replace("R", "1")

    return Integer.parseInt(row, 2) * 8 + Integer.parseInt(column, 2)
}
