fun main() {
    val lines = readLines("day5/input.in")
    val seats = lines.map { it.replace("F", "0").replace("B", "1")
                            .replace("R", "1").replace("L", "0")
    }.map { it.substring(0, 7).toInt(2) * 8 + it.substring(7).toInt(2) }.sorted()
    println("Part 1: ${seats.max()}")

    var expected = seats.first()
    for (value in seats) {
        if (value != expected) println("Part 2: ${value - 1}")
        expected = value + 1
    }
}
