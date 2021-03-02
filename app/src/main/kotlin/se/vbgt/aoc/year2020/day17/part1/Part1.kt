package se.vbgt.aoc.year2020.day17.part1

import java.io.File

data class Position(val x: Int, val y: Int, val z: Int)
typealias Positions = Set<Position>

fun part1(filename: String): Int {
    var state = readFileToState(filename)

    for (game in 1..6) {
        state = playConway3d(state)
    }

    return state.size
}

fun playConway3d(positions: Positions): Positions {
    val positionsToTest = positions.map {
        surroundingPositions(it)
    }.flatten()

    return positionsToTest.filter {
        val living = surroundingPositions(it).intersect(positions).size
        living == 3 || living == 2 && positions.contains(it)
    }.toSet()
}

val surroundingDeltas = generateSurroundingDeltas()
fun generateSurroundingDeltas(): List<Position> {
    val mutList = mutableListOf<Position>()
    for (z in -1..1)
        for (y in -1..1)
            for (x in -1..1) {
                if (x != 0 || y != 0 || z != 0) {
                    mutList.add(Position(x = x, y = y, z = z))
                }
            }
    return mutList.toList()
}

fun surroundingPositions(position: Position): Positions =
    surroundingDeltas.map {
        Position(
            x = it.x + position.x,
            y = it.y + position.y,
            z = it.z + position.z
        )
    }.toSet()

fun readFileToState(filename: String): Positions =
    File(filename).readLines()
        .mapIndexed { y, line ->
            line.mapIndexed { x, char -> if (char == '#') Position(x, y, 0) else null }
                .filterNotNull()
        }.flatten()
        .toSet()
