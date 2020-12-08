fun getResourceAsText(path: String): String {
    return object {}.javaClass.getResource(path).readText()
}

fun getLinesFromInput(): List<String> {
    return getResourceAsText("input").split('\n').filter { it.isNotBlank() }
}

fun getLinesFromInputWithBlank(): List<String> {
    return getResourceAsText("input").split('\n')
}