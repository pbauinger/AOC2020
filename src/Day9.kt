fun main() {
    val preambleSize = 25

    val numbers = readLines("day9/input.in")
        .filter { it != "" }
        .map { it.toLong() }

    var foundNumber = 0L
    for ((idx, number) in numbers.drop(preambleSize).withIndex()) {
        if (twoSum(numbers, idx, idx + preambleSize, number) == null) {
            println(number)
            foundNumber = number
            break
        }
    }

    for (idx1 in numbers.indices) {
        var currNumber = 0L
        var idx2 = idx1 + 1
        while (currNumber < foundNumber) {
            currNumber += numbers[idx2]
            idx2++
        }
        if (currNumber == foundNumber) {
            val sublist = numbers.subList(idx1, idx2 + 1)
            println(sublist.min()!! + sublist.max()!!)
            break
        }
    }
}

