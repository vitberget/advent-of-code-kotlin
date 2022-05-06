package se.vbgt.aoc.year2020.day23

import se.vbgt.aoc.year2020.day23.part1.part1
import se.vbgt.aoc.year2020.day23.part2.part2
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@OptIn(ExperimentalTime::class)
fun main() {
    val puzzleString = "784235916"
    val cupLabels = puzzleString.map { it - '0' } // Map string to list of cup labels (aka, numbers)

    val (part1result, part1time) = measureTimedValue { part1(cupLabels, 100) }
    println("part1 result: ${part1result}, took ${part1time}")

    println()

    val (part2result, part2time)  = measureTimedValue { part2(cupLabels, 10_000_000) }
    println("part2 result: ${part2result}, took ${part2time}")
}