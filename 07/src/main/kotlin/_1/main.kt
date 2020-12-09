package _1

import getLinesFromInput

fun main() {
    val lines: List<Node> = getLinesFromInput().toMutableList().map { line ->
        val name = line.split("contain").first().trim().removeSuffix(" bags").trim()
        val childrenString = line.split("contain")[1].trim().removeSuffix(".")
        val children: Map<String, Int> =
            childrenString.removeSuffix(".").split(",").map { it.trim() }.map {
                if (it != "no other bags") {
                    val count = it.filter { char -> char.isDigit() }
                    Pair(
                        it.substring(count.length).trim().removeSuffix("bags").removeSuffix("bag")
                            .trim(), count.toInt()
                    )
                } else {
                    null
                }
            }.filterNotNull().toMap()
        Node(name, children)
    }

    print(lines.filter {
        childrenAreGolden(it.childrenAsString, lines)
    }.size)
}

fun childrenAreGolden(children: Map<String, Int>, lines: List<Node>): Boolean {
    if (children.isEmpty()) {
        return false
    }
    if (children.any { it.key.contains("shiny gold") }) {
        return true
    }
    return children.map {
        childrenAreGolden(
            lines.first { line -> line.name == it.key }.childrenAsString,
            lines
        )
    }.any { it }
}


data class Node(
    val name: String,
    val childrenAsString: Map<String, Int>
)
