package _1

import getLinesFromInput
import org.apache.commons.collections4.queue.CircularFifoQueue
import java.util.*

fun main() {
    val lines = getLinesFromInput().map { it.toLong() }.toMutableList()

    val lastElements: Queue<Long> = CircularFifoQueue(25)

    for (i in 0..24) {
        lastElements.add(lines.removeFirst())
    }

    lines.forEach {
        if (!hasValuesThatSumTo(it, lastElements)) {
            println(it)
            return
        }
        lastElements.add(it)
    }
}

fun hasValuesThatSumTo(it: Long, lastElements: Queue<Long>): Boolean {
    return lastElements.flatMap { value1 ->
        lastElements.map { value2 ->
            value1 + value2
        }
    }
        .contains(it)
}

