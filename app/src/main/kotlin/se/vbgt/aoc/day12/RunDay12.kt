package se.vbgt.aoc.day12

import se.vbgt.aoc.day12.common.readFileIntoActions
import se.vbgt.aoc.day12.part1.day12part1
import se.vbgt.aoc.day12.part2.day12part2

fun main() {
    val actions = readFileIntoActions("puzzle12.txt")
    day12part1(actions)
    day12part2(actions)
}