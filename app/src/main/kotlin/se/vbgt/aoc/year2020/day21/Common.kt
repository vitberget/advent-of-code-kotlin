package se.vbgt.aoc.year2020.day21

import java.io.File

typealias WordSet = Set<String>
typealias WordSetPair = Pair<WordSet, WordSet>

fun fileToIngredientsAndAllergens(filename: String): List<WordSetPair> {
    return File(filename)
        .readLines()
        .map { lineToIngredientsAndAllergens(it) }
}

fun lineToIngredientsAndAllergens(line: String): WordSetPair {
    val (ingredientPart, allergenPart) = line.split(" (contains ")

    return Pair(
        ingredientPart
            .split("\\s".toRegex())
            .toSet(),
        allergenPart
            .replace("[,)]".toRegex(), "")
            .split("\\s".toRegex())
            .toSet()
    )
}

fun getAllergenIngredients(state: List<WordSetPair>): Map<String, WordSet> =
    state
        .flatMap { it.second }
        .toSet()
        .map { allergen ->
            allergen to state.filter { it.second.contains(allergen) }
                .map { it.first }
                .reduce { acc, set -> acc.intersect(set) }
        }
        .toMap()
