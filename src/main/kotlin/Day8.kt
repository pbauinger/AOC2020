import InstructionType_old.*

fun main() {
    val originalInstructions = readLines("day8/input.in")
        .filter { it != "" }
        .map { it.split(" ") }
        .map { Instruction_old(InstructionType_old.valueOf(it[0]), it[1].toInt()) }.toTypedArray()

    val (_, part1Acc) = executeInstructions(originalInstructions.copyOf())
    println(part1Acc)

    //just brute force part2
    val indices = originalInstructions.withIndex().filter { it.value.instType != acc }.map { it.index }
    for (idx in indices) {
        val instructions = originalInstructions.copyOf()
        val prevType = instructions[idx].instType
        instructions[idx] = instructions[idx].copy(instType = if(prevType == jmp) nop else jmp)
        val (success, part2Acc) = executeInstructions(instructions)
        if (success) {
            println(part2Acc)
            break
        }
    }
}

private fun executeInstructions(instructions: Array<Instruction_old>): Pair<Boolean, Int> {
    var acc = 0; var idx = 0
    var success = true
    while (idx < instructions.size) {
        if (instructions[idx].visited) {
            success = false
            break
        }
        instructions[idx] = instructions[idx].copy(visited = true)
        when (instructions[idx].instType) {
            InstructionType_old.acc -> {
                acc += instructions[idx].value
                idx++
            }
            jmp -> idx += instructions[idx].value
            nop -> idx++
        }
    }
    return Pair(success, acc)
}

private data class Instruction_old(val instType: InstructionType_old, val value: Int, val visited: Boolean = false)

private enum class InstructionType_old {
    acc, jmp, nop
}


