import InstructionType.*

fun main() {
    val originalInstructions = readLines("day8/input.in")
        .filter { it != "" }
        .map { it.split(" ") }
        .map { Instruction(InstructionType.valueOf(it[0]), it[1].toInt()) }.toTypedArray()

    val part1 = executeInstructions(originalInstructions.copyOf())
    println(part1.second)

    //just brute force part2
    val indices = originalInstructions.withIndex().filter { it.value.instType != acc }.map { it.index }
    for (idx in indices) {
        val instructions = originalInstructions.copyOf()
        val prefType = instructions[idx].instType
        instructions[idx] = instructions[idx].copy(instType = if(prefType == jmp) nop else jmp)
        val part2 = executeInstructions(instructions)
        if (part2.first) println(part2.second)
    }
}

private fun executeInstructions(instructions: Array<Instruction>): Pair<Boolean, Int> {
    var acc = 0; var idx = 0
    var success = true
    while (idx < instructions.size) {
        if (instructions[idx].visited) {
            success = false
            break
        }
        instructions[idx] = instructions[idx].copy(visited = true)
        when (instructions[idx].instType) {
            InstructionType.acc -> {
                acc += instructions[idx].value
                idx++
            }
            jmp -> idx += instructions[idx].value
            nop -> idx++
        }
    }
    return Pair(success, acc)
}

data class Instruction(val instType: InstructionType, val value: Int, val visited: Boolean = false)

enum class InstructionType {
    acc, jmp, nop
}


