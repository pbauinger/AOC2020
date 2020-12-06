fun main() {
    val groups = readText("day6/input.in").split("\n\n")
    val part1 = groups.sumBy { it.replace("\n", "").toSet().count() }

    val part2 = groups.map { it.split("\n").map { it.toSet() } }.sumBy {
        it.reduce { acc, set ->  acc.intersect(set)}.size
    }
    println("$part1, $part2")
}