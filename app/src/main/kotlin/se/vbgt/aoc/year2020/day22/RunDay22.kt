package se.vbgt.aoc.year2020.day22

import se.vbgt.aoc.year2020.day22.common.fileToCrabCombatState
import se.vbgt.aoc.year2020.day22.part1.part1
import kotlin.system.measureTimeMillis

fun main() {
    val puzzleCombatState = fileToCrabCombatState("puzzle22.txt")

    var p1: Int
    val t1 = measureTimeMillis {
        p1 = part1(puzzleCombatState)
    }
    println("Part1: $p1, took ${t1}ms")
}