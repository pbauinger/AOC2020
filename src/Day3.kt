fun main() {
    val lines = readLines("day3/input.in")
    println(cntT(lines, 3, 1))
    println(cntT(lines, 1, 1) * cntT(lines, 3, 1) * cntT(lines, 5, 1) * cntT(lines,7,1) * cntT(lines, 1, 2))
}

private fun cntT(lines: List<String>, dc: Int, dr: Int): Long {
    return lines.filterIndexed { i, v -> v[((i/dr) * dc) % v.length] == '#' && i % dr == 0}.size.toLong()
}