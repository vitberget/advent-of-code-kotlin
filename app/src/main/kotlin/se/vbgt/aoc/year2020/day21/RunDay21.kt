package se.vbgt.aoc.year2020.day21

import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@ExperimentalTime
fun main(args: Array<String>) {
//    val filename = "example21.txt"
    val filename = "puzzle21.txt"
    val (state, stateTime) = measureTimedValue { fileToState(filename) }
    println("file->state $stateTime")
    val (allergens, allergensTime) = measureTimedValue { getAllergenWords(state) }
    println("state->allergens $allergensTime")


    val (p1,t1) = measureTimedValue { part1(state, allergens) }
    println("Part 1: $p1 took $t1")

    val (p2,t2) = measureTimedValue { part2(allergens) }
    println("Part 2: $p2 took $t2")

    val d = 4

}

