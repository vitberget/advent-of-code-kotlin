package se.vbgt.aoc.year2020.day21

import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@ExperimentalTime
fun main() {
    val filename = "puzzle21.txt"

    val (state, stateTime) = measureTimedValue { fileToIngredientsAndAllergens(filename) }
    val (allergens, allergensTime) = measureTimedValue { getAllergenIngredients(state) }
    println("file->state $stateTime")
    println("state->allergens $allergensTime")

    val (p1,t1) = measureTimedValue { part1(state, allergens) }
    println("Part 1: $p1 took $t1")

    val (p2,t2) = measureTimedValue { part2(allergens) }
    println("Part 2: $p2 took $t2")
}

