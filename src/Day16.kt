fun main() {
    val input = readText("day16/input.in").split("\n\n")

    val rules = parseRules(input[0]).toMutableSet()
    val myTicket = parseTickets(input[1]).flatten()
    var otherTickets = parseTickets(input[2])

    println("Part 1: ${otherTickets.flatten().filter { v -> rules.none { it.check(v) } }.sum()}")

    var part2 = 1L
    otherTickets = otherTickets.filter { v -> v.all { va -> rules.any { it.check(va) } } }
    val columns = otherTickets.first().indices.toMutableList()
    while (columns.isNotEmpty()) {
        for (column in columns.toList()) {
            val columnValues = otherTickets.map { it[column] }
            val satRules = rules.filter { r -> columnValues.all { r.check(it) } }
            if (satRules.size != 1) continue
            if (satRules.first().name.startsWith("departure")) part2 *= myTicket[column]

            columns.remove(column)
            rules.remove(satRules.first())
        }
    }
    println("Part 2: $part2")
}

fun parseTickets(myTicket: String): List<List<Int>> {
    return myTicket.split("\n").drop(1).map { it.split(",").map { it.toInt() } }
}

fun parseRules(rules: String): MutableSet<Rule> {
    val set = mutableSetOf<Rule>()
    for (rule in rules.split("\n")) {
        val name = rule.split(":").first()
        val values = rule.split(":").last()

        val ranges = values.split("or").map { it.trim() }
            .map { it.split("-")[0].toInt()..it.split("-")[1].toInt() }

        set.add(Rule(name, ranges))
    }
    return set
}

data class Rule(val name: String, val allowedRanges: List<IntRange>) {
    fun check(number: Int) = allowedRanges.any { number in it }
}
