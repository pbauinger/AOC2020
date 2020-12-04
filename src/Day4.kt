fun main() {
    val lines = readText("day4/input.in").split("\n\n").map { it.replace("\n", " ") }
    println(part1(lines))
    println(part2(lines));
}

private fun part1(lines: List<String>): Int {
    val required = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
    var validCnt = 0
    for(line in lines) {
        val curr = required.toMutableList()
        val found = line.split(" ").map { it.split(":").first() }
        curr.removeAll(found)
        if(curr.size == 0) validCnt++
    }
    return validCnt
}

private fun part2(lines: List<String>): Int {
    var validCnt = 0
    for(line in lines) {
        var cnt = 0
        for(part in line.split(" ")) {
            if(part.split(":").size < 2) continue
            val (property, value) = part.split(":")

            when(property) {
                "byr" -> if (value.isDigit() && value.toInt() in 1920..2002) cnt++
                "iyr" -> if (value.isDigit() && value.toInt() in 2010..2020) cnt++
                "eyr" -> if (value.isDigit() && value.toInt() in 2020..2030) cnt++
                "hcl" -> if(value.length == 7 && Regex("#[0-9a-fA-F]+").matches(value)) cnt++
                "ecl" -> if(value in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")) cnt++
                "pid" -> if(value.length == 9 && value.isDigit()) cnt++
                "hgt" -> {
                    Regex("(\\d+)(cm|in)").matchEntire(value)?.let {
                        val(height, unit) = it.destructured
                        if (unit == "cm" && height.toInt() in 150..193) cnt++
                        if (unit == "in" && height.toInt() in 59..76) cnt++
                    }
                }
            }
        }
        if(cnt==7) validCnt++
    }
    return validCnt
}