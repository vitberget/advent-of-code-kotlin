package se.vbgt.aoc.year2020.day21

fun part2(allergens: Map<String, WordSet>): String =
    getUniqueAllergens(allergens)
        .toSortedMap()
        .map { it.value }
        .joinToString(separator = ",")

tailrec fun getUniqueAllergens(allergensWordsMap: Map<String, Set<String>>): Map<String, String> {
    val allergensWithMoreThanOneWord = allergensWordsMap
        .filter { it.value.size > 1 }
        .map { it.key }

    return if (allergensWithMoreThanOneWord.isEmpty()) {
        allergensWordsMap
            .map { it.key to it.value.first() }
            .toMap()
    } else {
        val wordsOnlyOnceInAllergens = allergensWordsMap
            .filter { it.value.size == 1 }
            .flatMap { it.value }
            .toSet()

        getUniqueAllergens(allergensWordsMap
            .map {
                it.key to
                        if (allergensWithMoreThanOneWord.contains(it.key))
                            it.value
                                .filterNot { word -> wordsOnlyOnceInAllergens.contains(word) }
                                .toSet()
                        else
                            it.value
            }
            .toMap())
    }
}
