package se.vbgt.aoc.day23

import se.vbgt.aoc.day23.part1.part1
import se.vbgt.aoc.day23.part2.part2
import kotlin.system.measureTimeMillis

fun main() {
    val numbers = "784235916".map { it - '0' } // Map string to list of numvers

    var p1 = ""
    val t1 = measureTimeMillis {
        p1 = part1(numbers, 100)
    }
    println("part1: ${p1}, ${t1}ms")


    var p2 = 0L
    val t2 = measureTimeMillis {
        p2 = part2(numbers, 10_000_000)
    }
    println("part2: ${p2}, ${t2}ms")
}