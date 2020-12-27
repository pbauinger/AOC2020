fun main() {
    val numbers: IntArray = readLines("day1/input.in").map { it.toInt() }.toIntArray()

    println(part1(numbers, 0, 2020))
    println(part2(numbers))
}

private fun part1(numbers: IntArray, startIdx: Int, target: Int): Int? {
    val seen = mutableSetOf<Int>()
    for (i in startIdx until numbers.size) {
        val num: Int = numbers[i]
        if (seen.contains(num)) return num * (target - num);
        seen.add(target - num)
    }
    return null
}

private fun part2(numbers: IntArray): Int? {
    for (i in 0 until numbers.size - 2) {
        val result = part1(numbers, i + 1, 2020 - numbers[i])
        result?.let { return numbers[i] * result }
    }
    return null
}
