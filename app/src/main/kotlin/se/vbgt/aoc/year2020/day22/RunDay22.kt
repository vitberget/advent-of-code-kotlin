package se.vbgt.aoc.year2020.day22

import se.vbgt.aoc.year2020.day22.common.fileToCrabCombatState
import se.vbgt.aoc.year2020.day22.part1.part1
import se.vbgt.aoc.year2020.day22.part2.part2
import kotlin.system.measureTimeMillis
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime
import kotlin.time.measureTimedValue

@ExperimentalTime
fun main() {
    val puzzleCombatState = fileToCrabCombatState("puzzle22.txt")

    val (p1,t1) = measureTimedValue {part1(puzzleCombatState)}
    println("Part1: ${p1}, took ${t1}")

    val (p2, t2) = measureTimedValue { part2(puzzleCombatState) }
    println("Part2: ${p2}, took ${t2}")
}

// Puzzle
// Part1: 33434, took 8.28ms
// Part2: 31657, took 75.6s