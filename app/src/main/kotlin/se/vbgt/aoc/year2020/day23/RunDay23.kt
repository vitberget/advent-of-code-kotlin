package se.vbgt.aoc.year2020.day23

import se.vbgt.aoc.year2020.day23.part1.part1
import se.vbgt.aoc.year2020.day23.part2.part2
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

/**
 * https://adventofcode.com/2020/day/23
 */
@OptIn(ExperimentalTime::class)
fun main() {
    val cupLabels = getCupLabels()

    val (part1result, part1time) = measureTimedValue { part1(cupLabels, 100) }
    println("part1 result: ${part1result}, took ${part1time}")
    // part1 result: 53248976, took 82.932865ms

    println()

    val (part2result, part2time)  = measureTimedValue { part2(cupLabels, 10_000_000) }
    println("part2 result: ${part2result}, took ${part2time}")
    // part2 result: 418819514477, took 9.989923576s
}

private fun getCupLabels(): List<Int> {
    val puzzleString = "784235916"
    return puzzleString.map { it.toNumber() }
}

private fun Char.toNumber(): Int = this - '0'