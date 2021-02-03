package se.vbgt.aoc.day7

import se.vbgt.aoc.day7.common.readFileIntoBags
import se.vbgt.aoc.day7.part1.day7part1
import se.vbgt.aoc.day7.part2.day7part2

fun main() {
    val bags = readFileIntoBags("puzzle7.txt")
    day7part1(bags)
    day7part2(bags)
}
