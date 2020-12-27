fun main() {
    val lines = readLines("day5/input.in")
    val seats = lines.map {
        it.replace("F", "0").replace("B", "1")
            .replace("R", "1").replace("L", "0")
    }.map { it.toInt(2) }.sorted()

    println("Part 1: ${seats.max()}")

    var expected = seats.first()
    for (seat in seats) {
        if (seat != expected) println("Part 2: ${seat - 1}")
        expected = seat + 1
    }
}