//only part 2
fun main() {
    val init = readLines("day17/input.in")
        .map { it.toCharArray() }

    val temp = mutableSetOf<Point4D>()
    for (x in init.indices) {
        temp.addAll(init[x].mapIndexed { idx, v -> if (v == '#') Point4D(x, idx, 0, 0) else null }.filterNotNull())
    }

    var gamefield = temp.toSet() //make it immutable to avoid stupid mistakes
    fun countNeighbors(point: Point4D) =
        gamefield.count {
            it != point
                    && it.x in point.x - 1..point.x + 1
                    && it.y in point.y - 1..point.y + 1
                    && it.z in point.z - 1..point.z + 1
                    && it.w in point.w - 1..point.w + 1
        }

    for (i in 1..6) {
        val toAdd = gamefield.flatMap { it.neighbors() }.groupingBy { it }.eachCount().filterValues { it == 3 }.keys
        val toRemove = gamefield.filter { countNeighbors(it) !in 2..3 }

        gamefield = (gamefield + toAdd) - toRemove;
    }
    print(gamefield.size)
}


private data class Point4D(val x: Int, val y: Int, val z: Int, val w: Int) {
    fun neighbors(): Set<Point4D> {
        val neighbors = mutableSetOf<Point4D>()
        for (i in -1..1) for (j in -1..1) for (k in -1..1) for (p in -1..1) {
            if (i != 0 || j != 0 || k != 0 || p != 0) neighbors.add(Point4D(x + i, y + j, z + k, w + p))
        }
        return neighbors;
    }
}