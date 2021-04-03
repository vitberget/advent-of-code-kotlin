package se.vbgt.aoc.year2020.day5.jni

import se.vbgt.jni.NativeRangeLib
import java.io.File

fun main() {
    val sortedSeats = fileToSeats("puzzle5.txt")
    println("Part1: ${sortedSeats.last()}")
    println("Part2: ${part2(sortedSeats)}")
}

fun fileToSeats(filename: String): List<Int> =
    File(filename)
        .readLines()
        .map { line -> getRow(line) * 8 + getColumn(line) }
        .sorted()

fun getRow(line: String): Int = rangeToInt(line, RangeJNI(0, 127), 'F')
fun getColumn(line: String): Int = rangeToInt(line.takeLast(3), RangeJNI(0, 7), 'L')

tailrec fun rangeToInt(line: String, range: RangeJNI, lowerChar: Char): Int =
    if (range.lower == range.higher)
        range.lower
    else
        rangeToInt(
            line = line.substring(1),
            range = if (line[0] == lowerChar) range.lowerHalf() else range.upperHalf(),
            lowerChar = lowerChar
        )

tailrec fun part2(seats: List<Int>): Int =
    if (seats[0] + 2 == seats[1])
        NativeRangeLib.incOnce(seats[0])
    else
        part2(seats.drop(1))

