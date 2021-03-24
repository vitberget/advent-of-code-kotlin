package se.vbgt.aoc.year2020.day3.a

import java.io.File
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

data class Position(
    val x: Int,
    val y: Int
)
typealias Tree = Position
typealias Step = Position

data class Forest(
    val width: Int,
    val height: Int,
    val trees: Set<Tree>
) {
    fun hasTreeAt(currentPosition: Position): Boolean =
        trees.contains(currentPosition.copy(x = currentPosition.x % width))
}

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
            .reduce { a, i -> a * i }
    }

    println("Part2: ${p2}, took ${t2}")
}

tailrec fun walkThrough(
    forest: Forest,
    step: Step,
    currentPosition: Position = Position(0, 0),
    treeHits: Long = 0
): Long =
    if (currentPosition.y > forest.height)
        treeHits
    else
        walkThrough(
            forest,
            step,
            currentPosition.copy(
                x = currentPosition.x + step.x,
                y = currentPosition.y + step.y
            ),
            treeHits + if (forest.hasTreeAt(currentPosition)) 1 else 0
        )

fun readFileIntoForest(filename: String): Forest =
    with(File(filename).readLines()) {
        Forest(
            width = this[0].length,
            height = this.size,
            trees = this
                .mapIndexed { y, rad -> rad.mapIndexedNotNull() { x, char -> if (char == '#') Tree(x, y) else null } }
                .flatten()
                .toSet()
        )
    }
