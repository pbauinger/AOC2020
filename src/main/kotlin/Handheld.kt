import Handheld.InstructionType.*

class Handheld {
    fun executeInstructions(instructions: Array<Instruction>): Pair<Boolean, Int> {
        var acc = 0; var idx = 0
        var success = true
        val visited = mutableSetOf<Int>()
        while (idx < instructions.size) {
            if (visited.contains(idx)) {
                success = false
                break
            }
            visited.add(idx)
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

    data class Instruction(val instType: InstructionType, val value: Int)

    enum class InstructionType {
        acc, jmp, nop
    }
}