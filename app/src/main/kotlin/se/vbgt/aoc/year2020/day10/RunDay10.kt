package se.vbgt.aoc.year2020.day10

import java.io.File
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

typealias Adapter = Long
typealias Adapters = List<Adapter>

@ExperimentalTime
fun main() {
    val filename = "puzzle10.txt"
    val adapters: Adapters = fileToAdapters(filename)

    val (p1, t1) = measureTimedValue { part1(adapters) }
    println("Part1: ${p1}, took ${t1}")

    val (p2, t2) = measureTimedValue { part2(adapters) }
    println("Part2: ${p2}, took ${t2}")

    // Part1: 2080, took 10.5ms
    // Part2: 690b8379398144, took 12.6ms
}


/**
 * Read file into numbers
 * Add 0 - the starting point
 * Add max value +3 - the target point
 *
 * From AoC:
 * "Treat the charging outlet near your seat as having an effective joltage rating of 0."
 * "In addition, your device has a built-in joltage adapter rated for 3 jolts higher than the highest-rated adapter in your bag."
 */
private fun fileToAdapters(filename: String): Adapters {
    val adaptersFromFile: Adapters = File(filename)
        .readLines()
        .mapNotNull { it.toLongOrNull() }
        .sorted()

    return listOf(0L) +
            adaptersFromFile +
            adaptersFromFile.last().plus(3)
}
