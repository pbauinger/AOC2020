fun main() {
    val preambleSize = 25

    val numbers = readLines("day9/input.in")
        .filter { it != "" }
        .map { it.toLong() }.toTypedArray()

    var idx = preambleSize
    var foundNumber = 0L
    for(number in numbers.drop(preambleSize)) {
        val twoSum = twoSum(numbers, idx - preambleSize, idx, number)
        if(twoSum == null) {
            println(number)
            foundNumber = number
            break
        }
        idx++
    }

    idx = 0
    while(idx < numbers.size) {
        var currNumber = 0L
        var idx2 = idx + 1
        while(currNumber < foundNumber) {
            currNumber += numbers[idx2]
            idx2++
        }
        if(currNumber == foundNumber) {
            val corrRange = numbers.copyOfRange(idx, idx2+1)
            println(corrRange.min()!! + corrRange.max()!!)
            break
        }
        idx++
    }
}

