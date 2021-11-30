package se.vbgt.aoc.year2020.day12

import se.vbgt.aoc.year2020.day12.common.readFileIntoActions
import se.vbgt.aoc.year2020.day12.part1.day12part1
import se.vbgt.aoc.year2020.day12.part2.day12part2
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@ExperimentalTime
fun main() {
    val (actions, ta) = measureTimedValue { readFileIntoActions("puzzle12.txt") }
    println("Reading actions took $ta")
    // Reading actions took 57.7ms

    val (p1, t1) = measureTimedValue { day12part1(actions) }
    println("Part1: $p1, took $t1")
    // Part1: 445, took 3.21ms

    val (p2, t2) = measureTimedValue { day12part2(actions) }
    println("Part2: $p2, took $t2")
    // Part2: 42495, took 1.91ms
}