fun main() {
    val lines = readLines("day7/input.in").filter { it != "" }

    val bagMapping = mutableMapOf<String, List<Pair<String, Int>>>()
    for (line in lines) {
        val parts = line.split(" bags contain ")
        val name = parts[0]

        val containedBags = mutableListOf<Pair<String, Int>>()
        if (parts[1] != "no other bags.") {
            val containedBagsStr = parts[1].removeSuffix(".").split(",").map { it.trim() }
            for (bagStr in containedBagsStr) {
                val fstSpaceIdx = bagStr.indexOf(' ')
                val lstSpaceIdx = bagStr.lastIndexOf(' ')
                val bag = Pair(bagStr.substring(fstSpaceIdx + 1, lstSpaceIdx), bagStr.substring(0, fstSpaceIdx).toInt())
                containedBags.add(bag)
            }
        }
        bagMapping[name] = containedBags
    }

    fun part1(bagColor: String): Set<String> {
        /*return bagMapping.filter { it.value.any { it.first == bagColor } }.keys.
                fold(setOf(bagColor)) {acc, s -> acc.union(part1(s))}*/

        val contained = bagMapping.filter { it.value.any { it.first == bagColor } }
        var set = setOf(bagColor)
        for (v in contained) set = set.union(part1(v.key))
        return set
    }

    fun part2(bagColor: String): Int {
        //return bagMapping[bagColor]!!.fold(0) { a, c -> a + c.second + c.second * part2(c.first) }

        var cnt = 0
        for(child in bagMapping[bagColor]!!) cnt += child.second + child.second * part2(child.first)
        return cnt
    }

    println(part1("shiny gold").size - 1)
    println(part2("shiny gold"))
}


