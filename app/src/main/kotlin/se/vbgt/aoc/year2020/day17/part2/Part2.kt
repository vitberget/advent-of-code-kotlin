package se.vbgt.aoc.year2020.day17.part2

import java.io.File

/**
 * Nothing really more special for my 4d solutions over my 3d solution,
 * except that there is now a fourth dimension of course.
 */
fun part2(filename: String): Int {
    var state = readFileToPositions(filename)

    for (game in 1..6) {
        state = playConway4d(state)
    }

    return state.size
}

private data class Position(
    val x: Int,
    val y: Int,
    val z: Int,
    val w: Int
)

private typealias Positions = Set<Position>

private fun playConway4d(positions: Positions): Positions =
    positions.map { surroundingPositions(it) }
        .flatten()
        .filter {
            with(surroundingPositions(it).intersect(positions).size) {
                this == 3 || this == 2 && positions.contains(it)
            }
        }.toSet()

private val surroundingDeltas =
    mutableListOf<Position>().apply {
        for (w in -1..1)
            for (z in -1..1)
                for (y in -1..1)
                    for (x in -1..1)
                        if (x != 0 || y != 0 || z != 0 || w != 0)
                            add(Position(x = x, y = y, z = z, w = w))
    }.toList()

private fun surroundingPositions(position: Position): Positions =
    surroundingDeltas.map {
        Position(
            x = it.x + position.x,
            y = it.y + position.y,
            z = it.z + position.z,
            w = it.w + position.w
        )
    }.toSet()

private fun readFileToPositions(filename: String): Positions =
    File(filename)
        .readLines()
        .mapIndexed { y, line -> lineToPositions(y, line) }
        .flatten()
        .toSet()

private fun lineToPositions(y: Int, line: String): List<Position> =
    line.mapIndexedNotNull { x, char ->
        if (char == '#')
            Position(x, y, 0, 0)
        else
            null
    }
