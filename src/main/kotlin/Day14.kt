fun main() {
    val values = readText("day14/input.in").split("mask = ").drop(1)

    part1(values)
    part2(values)
}

private fun part2(values: List<String>) {
    val memory = mutableMapOf<Long, Long>()

    for (block in values) {
        val lines = block.split("\n")
        val ones: Long = lines[0].replace('X', '0').toLong(2)
        val fixedMask: Long = lines[0].replace('1', 'X').replace('0', '1')
            .replace('X', '0').toLong(2)
        val floatingBits = lines[0]
            .replace('1', '0')
            .replace('X', '1')
            .mapIndexed {idx, c ->  ((c-'0').toLong())* 2L.pow(35 - idx)}.filter { it != 0L }

        fun writeToMemory(add : Long) {
            for (line in lines.drop(1).filter { it.isNotEmpty() }) {
                val parts = line.split("=").map { it.trim() }
                val adr = (parts[0].removePrefix("mem[").removeSuffix("]")).toLong()
                val value = parts[1].toLong()

                memory[adr.and(fixedMask) + ones + add] = value
            }
        }

        fun writeRecursive(accValue: Long, currIdx: Int) {
            if(currIdx >= floatingBits.size) return

            writeToMemory(floatingBits[currIdx] + accValue)
            writeRecursive(floatingBits[currIdx] + accValue, currIdx + 1)
            writeToMemory(accValue)
            writeRecursive(accValue, currIdx + 1)
        }
        writeRecursive(0,0)
    }
    println(memory.values.sum())
}


private fun part1(values: List<String>) {
    val memory = mutableMapOf<Long, Long>()

    for (block in values) {
        val lines = block.split("\n")
        val ones: Long = lines[0].replace('X', '0').toLong(2)
        val mask: Long = lines[0].replace('1', '0').replace('X', '1').toLong(2)

        for (line in lines.drop(1)) {
            if (line.isEmpty()) continue
            val parts = line.split("=").map { it.trim() }
            val adr: Long = (parts[0].removePrefix("mem[").removeSuffix("]")).toLong()

            memory[adr] = (parts[1].toLong().and(mask)) + ones
        }
    }
    println(memory.values.sum())
}