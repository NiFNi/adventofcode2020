package _2

import getLinesFromInputWithBlank


fun main() {
    val lines: MutableList<String> = getLinesFromInputWithBlank().toMutableList()
    val groups: MutableList<MutableList<String>> = mutableListOf(mutableListOf())
    for (line in lines) {
        if (line.isBlank()) {
            groups.add(mutableListOf())
        } else {
            groups.last().add(line)
        }
    }
    print(groups.map { countEveryoneYeses(it) }.reduceRight(Int::plus))
}

fun countEveryoneYeses(answers: List<String>): Int {
    return answers.joinToString().toCharArray().toSet().filter { question ->
        answers.all { it.contains(question) }
    }.size
}
