fun main() {
    val data = readText("day19/input.in").split("\n\n")

    val ruleTextInput = data[0].split("\n")
    val messageTextInput = data[1].split("\n").toMutableList()

    val ruleInput = ruleTextInput.map { it.split(":") }.map { it[0].trim().toInt() to it[1].trim() }.toMap()
    val checks = createChecks(ruleInput)

    var cnt = 0
    for (i in 1..50) for (j in 1..50) { //just to be sure :D
        for (message in messageTextInput.toList()) {
            val result = checks[0]!!.check(message, i, j)
            if (result != null && result.isEmpty()) {
                cnt++
                messageTextInput.remove(message)
            }
        }
        if(i==1 && j==1) println("Part1: $cnt")
    }
    println("Part2: $cnt")
}

fun createChecks(input: Map<Int, String>): Map<Int, CheckI> {
    val checks: MutableMap<Int, CheckI> = input
        .filter { it.value == "\"a\"" || it.value == "\"b\"" }
        .map { it.key to CheckTerminal(it.key, it.value[1]) }
        .toMap().toMutableMap()

    var remainingChecks = input.filter { it.key !in checks.keys }
    while (remainingChecks.isNotEmpty()) {
        // mega ugly, but who cares
        remainingChecks = input.filter { it.key !in checks.keys }
        val filteredIdx = remainingChecks.map {
            it.key to it.value.split(" | ").flatMap { it.split(" ") }.map { it.toInt() }.toSet() - checks.keys
        }.filter { it.second.isEmpty() }.map { it.first }
        for (idx in filteredIdx) checks[idx] = Check(idx, remainingChecks[idx]!!, checks)
    }
    return checks.toMap()
}

interface CheckI {
    val id: Int

    fun check(input: String, rep1: Int, rep2: Int): String?
}

class Check(override val id: Int, value: String, checks: Map<Int, CheckI>) : CheckI {
    private val subchecks = value.split(" | ").map { it.split(" ").map { checks[it.toInt()]!! } }

    override fun check(input: String, rep1: Int, rep2: Int): String? {
        if (input.isEmpty()) return null

        val rep = if (id == 8) rep1 else if (id == 11) rep2 else 1

        var curr: String? = input
        disjunction@ for (disjunction in subchecks) {
            curr = input //backup value
            for (conjunction in disjunction) for (i in 0 until rep) {
                curr = conjunction.check(curr!!, rep1, rep2)
                if (curr == null) continue@disjunction
            }
            return curr
        }
        return curr
    }
}

class CheckTerminal(override val id: Int, val v: Char) : CheckI {
    override fun check(input: String, rep1: Int, rep2: Int) = if (v == input[0]) input.substring(1) else null
}