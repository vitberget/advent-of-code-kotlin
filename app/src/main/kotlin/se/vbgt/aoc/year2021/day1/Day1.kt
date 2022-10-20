package se.vbgt.aoc.year2021.day1

import java.io.File

private fun zippie(delta: Int, numbers: List<Int>): Int =
        numbers.zip(numbers.drop(delta))
                .count { (a, b) -> a < b }

fun main() {
    val nums = File("puzzle-2021-01.txt")
            .readLines()
            .map { it.toInt() }

    println("part1 ${zippie(1, nums)}") // 1387
    println("part2 ${zippie(3, nums)}") // 1362
}