package _2

import getLinesFromInput
import org.apache.commons.collections4.queue.CircularFifoQueue
import java.util.*

fun main() {
    val lines = getLinesFromInput().map { it.toLong() }.toMutableList()

    val lastElements: Queue<Long> = CircularFifoQueue(25)

    for (i in 0..24) {
        lastElements.add(lines.removeFirst())
    }

    val result = lines.first {
        if (!hasValuesThatSumTo(it, lastElements)) {
            true
        } else {
            lastElements.add(it)
            false
        }
    }
    println(result)

    for (i in lines.indices) {
        val line = lines[i]
        var sum = line
        var j = 1
        while (sum < result) {
            sum += lines[i + j]
            if (sum == result) {
                val foundRange = lines.subList(i, i + j)
                println(foundRange)
                println(foundRange.minOrNull()!! + foundRange.maxOrNull()!!)
                return
            }
            j++
        }
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

