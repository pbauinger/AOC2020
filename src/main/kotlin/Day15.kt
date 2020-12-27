fun main() {
    val numbers = readText("day15/input.in").split(",").map { it.toInt() }
        .mapIndexed {idx, v -> v to mutableListOf(idx + 1) }.toMap().toMutableMap()

    fun addToMap(number: Int, idx: Int) {
        if(!numbers.containsKey(number)) {
            numbers[number] = mutableListOf(idx)
            return
        }
        if (numbers[number]!!.size == 1) numbers[number]!!.add(0)

        numbers[number]!![1] = numbers[number]!![0]
        numbers[number]!![0] = idx
    }

    var lastNumberSpoken = 5

    val start = numbers.size+1
    for(i in start..30000000) {
        val spokenCnt = numbers[lastNumberSpoken]!!.size

        if(spokenCnt == 1) {
            lastNumberSpoken = 0
            addToMap(lastNumberSpoken, i)
        }
        else if(spokenCnt == 2) {
            lastNumberSpoken = numbers[lastNumberSpoken]!![0] - numbers[lastNumberSpoken]!![1]
            addToMap(lastNumberSpoken, i)
        }
    }
    println(lastNumberSpoken)
}