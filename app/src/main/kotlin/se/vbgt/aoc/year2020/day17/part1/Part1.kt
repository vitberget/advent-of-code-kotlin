package se.vbgt.aoc.year2020.day17.part1

import java.io.File

fun part1(filename: String): Int {
    var state = readFileToPositions(filename)

    for (game in 1..6) {
        state = playConway3d(state)
    }

    return state.size
}

private data class Position(
    val x: Int,
    val y: Int,
    val z: Int
)

/**
 * Sets are awesome!
 */
private typealias Positions = Set<Position>

/**
 * Play a game of Conway in 3d
 *
 * First take all existing cells and use the cells surrounding these
 *
 * Then filter all the possible cells, and only keep the ones that match
 * the rules that keeps or turns a cell active
 */
private fun playConway3d(positions: Positions): Positions =
    positions.map { surroundingPositions(it) }
        .flatten()
        .filter {
            // Intersect = all item that exists in both sets, which gives us all the
            // surrounding cells of a positions that exists in the starting game positions
            // then count them
            with(surroundingPositions(it).intersect(positions).size) {
                // the rules from the game, with both dictates that a cell will be active
                // if it has three active cells. Left is that an active cell keeps it status
                // if it has two active cells as neighbours.
                this == 3 || this == 2 && positions.contains(it)
            }
        }.toSet()


/**
 * Delta positions surrounding a position. Filter out where all coordinates
 * are zero (this is the position itself
 */
private val surroundingDeltas = generateSurroundingDeltas()
private fun generateSurroundingDeltas(): List<Position> {
    val mutList = mutableListOf<Position>()
    for (z in -1..1)
        for (y in -1..1)
            for (x in -1..1)
                if (x != 0 || y != 0 || z != 0)
                    mutList.add(Position(x = x, y = y, z = z))

    return mutList.toList()
}

/**
 * Take the set of surrounding deltas and map them all on the
 * positions, giving all the cells surrounding that position
 */
private fun surroundingPositions(position: Position): Positions =
    surroundingDeltas.map {
        Position(
            x = it.x + position.x,
            y = it.y + position.y,
            z = it.z + position.z
        )
    }.toSet()

/**
 * Read characters #, and give them position x,y and z. (z is 0)
 * Put them all in a set of positions aka Positions
 */
private fun readFileToPositions(filename: String): Positions =
    File(filename)
        .readLines()
        .mapIndexed { y, line -> lineToPositions(y, line) }
        .flatten()
        .toSet()

private fun lineToPositions(y: Int, line: String): List<Position> =
    line.mapIndexedNotNull { x, char ->
        if (char == '#')
            Position(x, y, 0)
        else
            null
    }
