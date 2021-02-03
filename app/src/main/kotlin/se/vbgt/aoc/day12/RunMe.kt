package se.vbgt.aoc.day12

import se.vbgt.aoc.day12.common.readFileIntoActions
import se.vbgt.aoc.day12.part1.part1
import se.vbgt.aoc.day12.part2.part2

fun main() {
    val actions = readFileIntoActions("puzzle.txt")
    part1(actions)
    part2(actions)
}