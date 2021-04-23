package se.vbgt.aoc.year2020.day14

import java.io.File
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@OptIn(ExperimentalTime::class)
fun main() {
    val lines = File("puzzle14.txt").readLines()

    val (p1, t1) = measureTimedValue { part1(lines) }
    println("Part1: $p1, took $t1")
    // Part1: 9967721333886, took 106ms

    val (p2, t2) = measureTimedValue { part2(lines) }
    println("Part2: $p2, took $t2")
    //Part2: 4355897790573, took 11.8s
}