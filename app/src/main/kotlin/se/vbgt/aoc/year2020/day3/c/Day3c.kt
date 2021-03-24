package se.vbgt.aoc.year2020.day3.c

import java.io.File
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

data class Position(
    val x: Int,
    val y: Int
)
typealias Step = Position
typealias Forest = List<String>

@ExperimentalTime
fun main() {
    val (forest,t) = measureTimedValue { readFileIntoForest("puzzle3.txt") }
    println("Forest in ${t}")

    part1(forest)
    part2(forest)
}

@ExperimentalTime
private fun part1(forest: Forest) {
    val (p1, t1) = measureTimedValue {
        walkThrough(
            forest,
            Step(3, 1)
        )
    }
    println("Part1: ${p1}, took ${t1}")
}

@ExperimentalTime
private fun part2(forest: Forest) {
    val (p2, t2) = measureTimedValue {
        listOf(
            Step(1, 1),
            Step(3, 1),
            Step(5, 1),
            Step(7, 1),
            Step(1, 2)
        )
            .map { walkThrough(forest, it) }
            .reduce { acc, item -> acc * item }
    }

    println("Part2: ${p2}, took ${t2}")
}

tailrec fun walkThrough(
    forest: Forest,
    step: Step,
    trees: Long = 0,
    x: Int = 0
): Long =
    if (forest.isEmpty())
        trees
    else {
        val line = forest[0]
        val modifier = if (line[x % line.length] == '#') 1 else 0
        walkThrough(
            forest.drop(step.y),
            step,
            trees + modifier,
            x + step.x
        )
    }

fun readFileIntoForest(filename: String): Forest =
    File(filename).readLines()
