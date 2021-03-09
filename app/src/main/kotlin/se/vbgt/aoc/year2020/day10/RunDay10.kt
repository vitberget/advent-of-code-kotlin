package se.vbgt.aoc.year2020.day10

import se.vbgt.aoc.year2020.day10.part1.part1
import se.vbgt.aoc.year2020.day10.part2.part2
import java.io.File
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@ExperimentalTime
fun main() {
//    val filename = "example10a.txt"
    val filename = "puzzle10.txt"
    val adapters: List<Long> = File(filename)
        .readLines()
        .mapNotNull { it.toLongOrNull() }
        .sorted()

    val (p1, t1) = measureTimedValue { part1(adapters) }
    println("Part1: ${p1}, took ${t1}")

    val (p2, t2) = measureTimedValue { part2(adapters) }
    println("Part2: ${p2}, took ${t2}")
}
// Part1: 2080, took 10.5ms
// Part2: 6908379398144, took 12.6ms