package _2

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

    val goldBag = lines.first { it.name == "shiny gold" }

    print(countBags(goldBag, lines))
}

fun countBags(goldBag: Node, lines: List<Node>): Int {
    val numberOfDirectChildren = goldBag.childrenAsString.map { it.value }.fold(0, Int::plus)

    return numberOfDirectChildren +
            goldBag.childrenAsString.map {
                Pair(
                    lines.first { line -> line.name == it.key },
                    it.value
                )
            }.map { countBags(it.first, lines) * it.second }.fold(0, Int::plus)

}


data class Node(
    val name: String,
    val childrenAsString: Map<String, Int>
)
