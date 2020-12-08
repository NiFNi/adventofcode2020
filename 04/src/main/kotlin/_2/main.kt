package _2

import getResourceAsText

val NEEDED = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

fun main() {
    val lines: MutableList<String> = getResourceAsText("input").split("\n").toMutableList()
    val passports = mutableListOf<Passport>()
    processLines(lines, passports, Passport(mutableMapOf()))
    println(passports.count { passport ->
        NEEDED.map {
            passport.entries[it]
        }.none { it == null } && validate(passport)
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

fun validate(passport: Passport): Boolean {
    val entries = passport.entries
    val byr = entries["byr"]!!.toInt()
    if (byr < 1920 || byr > 2002) {
        return false
    }

    val iyr = entries["iyr"]!!.toInt()
    if (iyr < 2010 || iyr > 2020) {
        return false
    }

    val eyr = entries["eyr"]!!.toInt()
    if (eyr < 2020 || eyr > 2030) {
        return false
    }

    val hgt = entries["hgt"]!!
    if (isHgtInvalid(hgt)) {
        return false
    }

    val hcl = entries["hcl"]!!
    if (isHclInvalid(hcl)) {
        return false
    }

    val ecl = entries["ecl"]!!
    if (isEclInvalid(ecl)) {
        return false
    }

    val pid = entries["pid"]!!
    if (pid.length != 9 || !isNumeric(pid)) {
        return false
    }


    return true
}

fun isEclInvalid(ecl: String): Boolean {
    return ecl !in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
}

fun isHclInvalid(hcl: String): Boolean {
    if (hcl.length != 7) {
        return true
    }
    return hcl.first() != '#' || !hcl.substring(1).all { it.isLetterOrDigit() }
}

fun isHgtInvalid(hgt: String): Boolean {
    return when (hgt.length) {
        5 -> {
            val height = hgt.substring(0, 3)
            !isNumeric(height) || hgt.substring(3, 5) != "cm" || height.toInt() !in 150..193
        }
        4 -> {
            val height = hgt.substring(0, 2)
            !isNumeric(height) || hgt.substring(2, 4) != "in" || height.toInt() !in 59..76
        }
        else -> {
            true
        }
    }
}

fun isNumeric(string: String): Boolean {
    return string.chars().allMatch(Character::isDigit)
}

data class Passport(val entries: MutableMap<String, String>)