package _1

import getResourceAsText

val NEEDED = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

fun main() {
    val lines: MutableList<String> = getResourceAsText("input").split("\n").toMutableList()
    val passports = mutableListOf<Passport>()
    processLines(lines, passports, Passport(mutableMapOf()))
    println(passports.count { passport ->
        NEEDED.map {
            passport.entries[it]
        }.none { it == null }
    })
}

fun processLines(lines: MutableList<String>, passports: MutableList<Passport>, currentPassport: Passport) {
    if (lines.isEmpty()) {
        return
    }
    val it = lines.removeFirst()
    if (it.isBlank()) {
        passports.add(currentPassport)
        processLines(lines, passports, Passport(mutableMapOf()))
    } else {
        addLineToPassport(it, currentPassport)
        processLines(lines, passports, currentPassport)
    }
}

fun addLineToPassport(line: String, passport: Passport) {
    val entries = line.split(" ")
    entries.forEach {
        val entry = it.split(":")
        passport.entries[entry[0]] = entry[1]
    }
}

data class Passport(val entries: MutableMap<String, String>)