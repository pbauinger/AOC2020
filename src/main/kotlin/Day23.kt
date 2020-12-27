fun main() {
    val input = "962713854".toCharArray().map { it - '0' }
    printList(process(input, 100))

    val part2 = process((input + (10..1_000_000)), 10_000_000)
    print(part2[1].toLong() * part2[part2[1]])
}

private fun process(input: List<Int>, steps: Int): IntArray {
    val min = input.minOrNull()!!
    val max = input.maxOrNull()!!
    val removed = IntArray(3)
    val ll = IntArray(input.size + 1)
    for ((idx, v) in input.withIndex()) {
        ll[v] = input[(idx + 1) % input.size]
    }

    var curr = input.first()
    fun pickUpCups() {
        var temp = curr
        for (i in 0..2) {
            temp = ll[temp]
            removed[i] = temp
        }
        ll[curr] = ll[temp]
    }

    fun findDestination(): Int {
        var currVal = curr - 1;
        while (true) {
            if (currVal < min) {
                currVal = max
                continue
            }
            if (currVal !in removed) return currVal
            currVal--
        }
    }

    fun placeCups(dest: Int) {
        val prevNext = ll[dest]
        ll[dest] = removed[0]
        ll[removed.last()] = prevNext
    }

    repeat(steps) {
        pickUpCups()
        val destinationCup = findDestination()
        placeCups(destinationCup)
        curr = ll[curr]
    }

    return ll
}

fun printList(ll: IntArray) {
    var curr = ll[1]
    for (i in 1..8) {
        print(curr)
        curr = ll[curr]
    }
    println()
}