package se.vbgt.aoc.year2021.day1

import java.io.File

private fun zipSolver(delta: Int, numbers: List<Int>): Int =
    numbers.drop(delta)
        .zip(numbers)
        .count { (a, b) -> a > b }

fun main() {
    val depthNumbers = File("puzzle-2021-01.txt")
        .readLines()
        .map { it.toInt() }

    println("part1 ${zipSolver(1, depthNumbers)}") // 1387
    println("part2 ${zipSolver(3, depthNumbers)}") // 1362
}