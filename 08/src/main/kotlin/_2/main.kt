package _2

import getLinesFromInput

fun main() {
    val lines = getLinesFromInput().map { parseLine(it) }

    var executedIndices: MutableList<Int>
    var acc = 0
    var index = 0
    var toChangeIndex = 0
    var toChangeCounter: Int
    while (index != lines.size) {
        index = 0
        acc = 0
        toChangeCounter = 0
        executedIndices = mutableListOf()

        while (!executedIndices.contains(index)) {
            executedIndices.add(index)
            if (index >= lines.size) {
                break
            }
            val line = lines[index]
            val stopExecution = index == lines.size
            when (line) {
                is Nop -> {
                    if (toChangeCounter == toChangeIndex) {
                        index += line.i
                        toChangeCounter++
                    } else {
                        index++
                        toChangeCounter++
                    }
                }
                is Acc -> {
                    acc += line.i
                    index++
                }
                is Jmp -> {
                    if (toChangeCounter == toChangeIndex) {
                        index++
                        toChangeCounter++
                    } else {
                        index += line.i
                        toChangeCounter++
                    }
                }
            }

            if (stopExecution) {
                break
            }

        }
        toChangeIndex++
    }
    print(acc)

}

fun parseLine(line: String): Instr {
    val operation = line.substring(0, 3)
    return when (operation) {
        "nop" -> Nop(getNumber(line))
        "acc" -> Acc(getNumber(line))
        "jmp" -> Jmp(getNumber(line))
        else -> throw IllegalArgumentException()
    }
}

fun getNumber(line: String): Int {
    if (line.contains("+")) {
        return line.split("+")[1].trim().toInt()
    }
    return line.split("-")[1].trim().toInt() * -1
}

open class Instr(val i: Int)

class Acc(i: Int) : Instr(i)
class Nop(i: Int) : Instr(i)
class Jmp(i: Int) : Instr(i)