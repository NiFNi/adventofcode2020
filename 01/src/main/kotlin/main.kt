fun main() {
    val input = getResourceAsText("input").split('\n').filter { it.isNotBlank() }.map { it.toInt() }
    input.forEach { first ->
        input.forEach { second ->
            input.forEach { third ->
                if (first + second + third == 2020) {
                    println(first * second * third)
                }
            }
        }
    }
}
