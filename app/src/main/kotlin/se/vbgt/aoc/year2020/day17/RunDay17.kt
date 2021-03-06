package se.vbgt.aoc.year2020.day17

import se.vbgt.aoc.year2020.day17.part1.part1
import se.vbgt.aoc.year2020.day17.part2.part2
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@ExperimentalTime
fun main() {
//    val filename = "example17.txt"
    val filename = "puzzle17.txt"

    val (p1,t1) = measureTimedValue { part1(filename) }
    println("Part1: ${p1}, took ${t1}")

    val (p2,t2) = measureTimedValue { part2(filename) }
    println("Part2: ${p2}, took ${t2}")
}

// Puzzle
// Part1: 286, took 209ms
// Part2: 960, took 3.05s
