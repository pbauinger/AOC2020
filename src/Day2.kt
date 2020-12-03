fun main() {
    val lines = readLines("day2/input.in")

    println(part1(lines))
    println(part2(lines))
}

private fun part1(lines: List<String>): Int {
    return lines.filter { line ->
        val (range, char, password) = line.split(" ")
        val (low, high) = range.split("-").map { it.toInt() }
        password.filter { it == char[0] }.length in low..high
    }.count()
}

private fun part2(lines: List<String>): Int {
    return lines.filter { line ->
        val (range, char, password) = line.split(" ")
        val (low, high) = range.split("-").map { it.toInt() }
        (password[low - 1] == char[0]).xor(password[high - 1] == char[0])
    }.count()
}