import Handheld.*
import Handheld.InstructionType.*

fun main() {
    val handheld = Handheld()
    val originalInstructions = readLines("day8/input.in")
        .filter { it != "" }
        .map { it.split(" ") }
        .map { Instruction(InstructionType.valueOf(it[0]), it[1].toInt()) }.toTypedArray()

    val (_, part1Acc) = handheld.executeInstructions(originalInstructions.copyOf())
    println(part1Acc)

    //just brute force part2
    val indices = originalInstructions.withIndex().filter { it.value.instType != acc }.map { it.index }
    for (idx in indices) {
        val instructions = originalInstructions.copyOf()
        val prevType = instructions[idx].instType
        instructions[idx] = instructions[idx].copy(instType = if(prevType == jmp) nop else jmp)
        val (success, part2Acc) = handheld.executeInstructions(instructions)
        if (success) {
            println(part2Acc)
            break
        }
    }
}