package se.vbgt.aoc.year2020.day23

import se.vbgt.aoc.year2020.day23.part1.part1
import se.vbgt.aoc.year2020.day23.part2.part2
import kotlin.system.measureTimeMillis

fun main() {
    val cupLabels = "784235916".map { it - '0' } // Map string to list of cup labels (aka, numbers)

    var part1result: String
    val part1time = measureTimeMillis {
        part1result = part1(cupLabels, 100)
    }
    println("part1: ${part1result}, ${part1time}ms")


    var part2result: Long
    val part2time = measureTimeMillis {
        part2result = part2(cupLabels, 10_000_000)
    }
    println("part2: ${part2result}, ${part2time}ms")
}