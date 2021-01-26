package se.vbgt.aoc.day12

import se.vbgt.aoc.day12.common.readFileIntoInstructions
import se.vbgt.aoc.day12.part1.part1
import se.vbgt.aoc.day12.part2.part2

fun main() {
    val ops = readFileIntoInstructions("puzzle.txt")
    part1(ops)
    part2(ops)
}