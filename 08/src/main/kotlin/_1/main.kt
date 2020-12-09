package _1

import getLinesFromInput

fun main() {
    val lines = getLinesFromInput().map { parseLine(it) }

    val executedIndices = mutableListOf<Int>()
    var acc = 0
    var index = 0

    while (!executedIndices.contains(index)) {
        executedIndices.add(index)
        val line = lines[index]

        if (index == lines.size) {
            break
        }
        when (line) {
            is Nop -> index++
            is Acc -> {
                acc += line.i
                index++
            }
            is Jmp -> index += line.i
        }

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