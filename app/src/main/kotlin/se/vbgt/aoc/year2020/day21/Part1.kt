package se.vbgt.aoc.year2020.day21

fun part1(state: List<WordSetPair>, allergens: Map<String, WordSet>): Int {
    val allergenIngredients = allergens
        .flatMap { it.value }
        .toSet()

    val impossibleAllergenIngredients = state
        .flatMap { it.first }
        .filterNot { allergenIngredients.contains(it) }
        .toSet()

    return state
        .map { it.first }
        .map { it.intersect(impossibleAllergenIngredients) }
        .sumBy { it.size }
}