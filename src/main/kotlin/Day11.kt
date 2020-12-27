fun main() {
    part1()
    part2()
}

private fun part1() {
    val seats = readInput()
    val getNeighbours = { seat: Vec2 ->
        seats.filter { it != seat && (it.x - seat.x) in -1..1 && (it.y - seat.y) in -1..1 }
    }
    simulate(seats, getNeighbours, 4)
}

private fun part2() {
    val seats = readInput()
    val getNeighbours = { seat: Vec2 ->
        val neighbours = mutableListOf<Vec2>()

        val horizontal = seats.filter { it.y == seat.y }.sortedBy { it.x }
        horizontal.lastOrNull { it.x < seat.x }?.let { neighbours.add(it) }
        horizontal.firstOrNull { it.x > seat.x }?.let { neighbours.add(it) }

        val vertical = seats.filter { it.x == seat.x }.sortedBy { it.y }
        vertical.lastOrNull { it.y < seat.y }?.let { neighbours.add(it) }
        vertical.firstOrNull { it.y > seat.y }?.let { neighbours.add(it) }

        val diagonal = seats.filter { (it.x - it.y) == (seat.x - seat.y) }.sortedBy { it.y }
        diagonal.lastOrNull { it.y < seat.y }?.let { neighbours.add(it) }
        diagonal.firstOrNull { it.y > seat.y }?.let { neighbours.add(it) }

        val diagonal2 = seats.filter { (it.x + it.y) == (seat.x + seat.y)}.sortedBy { it.x }
        diagonal2.lastOrNull { it.x < seat.x }?.let { neighbours.add(it) }
        diagonal2.firstOrNull { it.x > seat.x }?.let { neighbours.add(it) }

        neighbours.toList()
    }
    simulate(seats, getNeighbours, 5)
}

private fun simulate(seats: List<Vec2>, getNeighbours: (Vec2) -> List<Vec2>, occThreshold: Int) {
    val neighbourLookup = seats.map { it to getNeighbours(it) }.toMap()
    while(true) {
        val seatsToOccupy = seats.filter { !it.occ }.filter { neighbourLookup[it]!!.all { !it.occ } }
        val seatsToEmpty = seats.filter { it.occ }.filter { neighbourLookup[it]!!.count { it.occ } >= occThreshold}

        if(seatsToOccupy.isEmpty() && seatsToEmpty.isEmpty()) break

        seatsToOccupy.forEach { s -> s.occ = true }
        seatsToEmpty.forEach { s -> s.occ = false }
    }
    println(seats.count{it.occ})
}

private fun readInput(): List<Vec2> {
    val rows = readLines("day11/input.in")
    val seats = mutableListOf<Vec2>()
    for ((idx, row) in rows.withIndex()) {
        seats.addAll(row.withIndex().filter { it.value != '.' }.map { Vec2(it.index, idx) })
    }
    return seats.toList()
}

data class Vec2(val x: Int, val y: Int) {
    var occ = false
}