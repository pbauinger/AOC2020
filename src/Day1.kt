fun main() {
    val numbers = readLines("day1/input.in").map { it.toInt() }

    println(part1(numbers, 0, 2020))
    println(part2(numbers))
}

private fun part1(numbers: List<Int>, startIdx: Int, target: Int): Int {
    val seen = mutableSetOf<Int>()
    for (i in startIdx until numbers.size) {
        val num: Int = numbers[i]
        if (seen.contains(num)) return num * (target - num);
        seen.add(target - num)
    }
    return Int.MIN_VALUE;
}

private fun part2(numbers: List<Int>): Int {
    for (i in 0 until numbers.size - 2) {
        val result = part1(numbers, i + 1, 2020 - numbers[i])
        if (result > Int.MIN_VALUE) return numbers[i] * result
    }
    return Int.MIN_VALUE;
}