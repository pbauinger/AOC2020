fun main() {
    val originalInstructions = readLines("day8/input.in")
        .filter { it != "" }
        .map { it.split(" ") }
        .map { Instruction(InstructionType.valueOf(it.first()), it.last().toInt()) }.toTypedArray()

    val part1 = executeInstructions(originalInstructions.copyOf())
    println(part1.second)

    //just brute force part2
    val replaceElements = originalInstructions.filter { it.instType != InstructionType.acc }
    for (element in replaceElements) {
        val instructions = originalInstructions.copyOf()
        val idx = instructions.indexOf(element)
        if (element.instType == InstructionType.jmp) {
            instructions[idx] = instructions[idx].copy(instType = InstructionType.nop)
        } else if (element.instType == InstructionType.nop) {
            instructions[idx] = instructions[idx].copy(instType = InstructionType.jmp)
        }
        val part2 = executeInstructions(instructions)
        if (part2.first) println(part2.second)
    }
}

private fun executeInstructions(instructions: Array<Instruction>): Pair<Boolean, Int> {
    var acc = 0
    var idx = 0
    var success = true
    while (idx < instructions.size) {
        val instruction = instructions[idx]
        if (instruction.visited) {
            success = false
            break
        }
        instructions[idx] = instructions[idx].copy(visited = true)
        when (instruction.instType) {
            InstructionType.acc -> {
                acc += instruction.value
                idx++
            }
            InstructionType.jmp -> idx += instruction.value
            InstructionType.nop -> idx++
        }
    }
    return Pair(success, acc)
}

data class Instruction(val instType: InstructionType, val value: Int, val visited: Boolean = false)

enum class InstructionType {
    acc, jmp, nop
}


