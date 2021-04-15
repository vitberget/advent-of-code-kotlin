package se.vbgt.aoc.year2020.day5.binary

import se.vbgt.aoc.year2020.day5.part2
import java.io.File

fun main() {
    println("Day 5")
    val sortedSeats = fileToSeats("puzzle5.txt")
    println("Part1: ${sortedSeats.last()}")
    println("Part2: ${part2(sortedSeats)}")
}

fun fileToSeats(filename: String): List<Int> =
    File(filename)
        .readLines()
        .map {
            it.replace('F', '0')
                .replace('L', '0')
                .replace('B', '1')
                .replace('R', '1')
        }
        .map { it.toInt(2) }
        .sorted()

tailrec fun part2b(seats: List<Int>): Int =
    if (seats[0] + 2 == seats[1])
        seats[0] + 1
    else
        part2b(seats.drop(1))

