fun main() {
    val input = readLines("day21/input.in")

    val entries = mutableListOf<Pair<Set<String>, Set<String>>>()
    val remainingIngredients = mutableSetOf<String>()
    val remainingAllergens = mutableSetOf<String>()
    val allergensToIngredients = mutableMapOf<String, String>()

    for(line in input) {
        val split = line.split(" (contains ")
        val i = split[0].split(" ").map { it.trim() }.toSet()
        val a = split[1].removeSuffix(")").split(",").map { it.trim() }.toSet()

        entries.add(Pair(i, a))
        remainingIngredients.addAll(i)
        remainingAllergens.addAll(a)
    }

    while(remainingAllergens.isNotEmpty()) {
        for (a in remainingAllergens.toSet()) {
            val ingredients =
                entries.filter { it.second.contains(a) }
                    .map { it.first - allergensToIngredients.values }
                    .reduce { acc, set -> acc.intersect(set) }

            if (ingredients.size == 1) {
                allergensToIngredients[a] = ingredients.first()
                allergensToIngredients[a] = ingredients.first()
                remainingAllergens.remove(a)
                remainingIngredients.remove(ingredients.first())
            }
        }
    }
    val cnt = entries.map { it.first }.flatten().count { remainingIngredients.contains(it) }

    println(allergensToIngredients.toSortedMap().values.joinToString(","))
    println(cnt)
}