package se.vbgt.aoc.year2020.day5.binary

import java.io.File

fun main() {
    println("Day 5")
    val sortedSeats = fileToSeats("puzzle5.txt")
    println("Part1: ${sortedSeats.last()}")
    println("Part2: ${part2(sortedSeats)}")
}

private val oneLetters = "[BR]".toRegex()
private val zeroLetters = "[FL]".toRegex()

private fun fileToSeats(filename: String): List<Int> =
    File(filename)
        .readLines()
        .map {
            it.replace(oneLetters, "1")
                .replace(zeroLetters, "0")
        }
        .map { it.toInt(2) }
        .sorted()

private tailrec fun part2(seats: List<Int>): Int =
    if (seats[0] + 2 == seats[1])
        seats[0] + 1
    else
        part2(seats.drop(1))

