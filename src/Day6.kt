fun main() {
    val groups = readText("day6/input.in").split("\n\n")
    val part1 = groups.sumBy { it.replace("\n", "").toSet().count() }

    val part2 = groups.map { it.split("\n").map { it.toCharArray().toSet() } }.sumBy {
        it.fold(it.first()) { acc, i -> acc.intersect(i) }.size
    }
    println("$part1, $part2")
}