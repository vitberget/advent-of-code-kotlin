package se.vbgt.aoc.year2020.day21

fun part2(allergens: Map<String, WordSet>): String =
    getUniqueAllergens(allergens)
        .toSortedMap()
        .map { it.value }
        .joinToString(separator = ",")

tailrec fun getUniqueAllergens(allergens: Map<String, Set<String>>): Map<String, String> {
    val mores = allergens
        .filter { it.value.size > 1 }
        .map { it.key }

    return if (mores.isEmpty()) {
        allergens
            .map { it.key to it.value.first() }
            .toMap()
    } else {
        val oneWordOnlies = allergens
            .filter { it.value.size == 1 }
            .flatMap { it.value }
            .toSet()

        getUniqueAllergens(allergens
            .map {
                if (mores.contains(it.key))
                    it.key to it.value.filterNot { v -> oneWordOnlies.contains(v) }.toSet()
                else
                    it.key to it.value
            }
            .toMap())
    }
}
