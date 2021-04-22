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
}