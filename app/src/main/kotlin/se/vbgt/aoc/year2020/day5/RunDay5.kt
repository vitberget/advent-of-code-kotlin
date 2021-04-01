package se.vbgt.aoc.year2020.day5

import java.io.File

fun main() {
    val seats = fileToSeats("puzzle5.txt")
    println("Part1: ${seats.maxByOrNull { it }}")
    println("Part2: ${part2(seats.sorted())}")
}

fun fileToSeats(filename: String): List<Int> =
    File(filename)
        .readLines()
        .map { line -> getRow(line) * 8 + getColumn(line) }

fun getRow(line: String): Int = getRange(line, Range(0, 127), 'F')
fun getColumn(line: String): Int = getRange(line.takeLast(3), Range(0, 7), 'L')
tailrec fun getRange(line: String, range: Range, lowerChar: Char): Int =
    if (range.lower == range.higher)
        range.lower
    else
        getRange(
            line = line.substring(1),
            range = if (line[0] == lowerChar) range.lowerHalf() else range.upperHalf(),
            lowerChar = lowerChar
        )

tailrec fun part2(seats: List<Int>): Int =
    if (seats[0] + 2 == seats[1])
        seats[0] + 1
    else
        part2(seats.drop(1))

