package se.vbgt.aoc.year2020.day22

import se.vbgt.aoc.year2020.day22.common.fileToCrabCombatState
import se.vbgt.aoc.year2020.day22.part1.part1
import se.vbgt.aoc.year2020.day22.part2.part2
import kotlin.system.measureTimeMillis

fun main() {
    val puzzleCombatState = fileToCrabCombatState("puzzle22.txt")

    val (p1, t1) = logTime { part1(puzzleCombatState)}
    println("Part1: ${p1}, took ${t1}ms")

    val (p2, t2) = logTime { part2(puzzleCombatState) }
    println("Part2: ${p2}, took ${t2}ms")
}

fun logTime(function: () -> Any): Pair<Any,Long> {
    var a : Any
    val t = measureTimeMillis {
        a = function()
    }
    return a to t
}