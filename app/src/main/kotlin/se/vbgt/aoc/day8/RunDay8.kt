package se.vbgt.aoc.day8

import se.vbgt.aoc.day8.common.fileToInstructions
import se.vbgt.aoc.day8.part1.day8part1
import se.vbgt.aoc.day8.part2.day8part2

fun main() {
    val instructions = fileToInstructions("puzzle8.txt")

    day8part1(instructions)
    day8part2(instructions)
}