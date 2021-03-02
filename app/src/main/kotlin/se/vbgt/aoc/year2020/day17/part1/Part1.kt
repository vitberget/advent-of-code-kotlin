package se.vbgt.aoc.year2020.day17.part1

import java.io.File

data class Position(
    val x: Int,
    val y: Int,
    val z: Int
)
/**
 * Sets are awesome!
 */
typealias Positions = Set<Position>

fun part1(filename: String): Int {
    var state = readFileToState(filename)

    for (game in 1..6) {
        state = playConway3d(state)
    }

    return state.size
}

/**
 * Play a game of Conway in 3d
 *
 * First take all existing cells and use the cells surrounding theese
 *
 * Then filter all the possible cells, and only keep the ones that match
 * the rules that keeps or turns a cell active
 */
fun playConway3d(positions: Positions): Positions {
    val positionsToTest = positions.map {
        surroundingPositions(it)
    }.flatten()

    return positionsToTest.filter {
        // Intersect = all item that exists in both sets, which gives us all the
        // surrounding cells of a positions that exists in the starting game positions
        // then count them
        val noOfActiveNeighbourCells = surroundingPositions(it).intersect(positions).size
        // the rules from the game, with both dictates that a cell will be active
        // if it has three active cells. Left is that an active cell keeps it status
        // if it has two active cells as neighbours.
        noOfActiveNeighbourCells == 3 || noOfActiveNeighbourCells == 2 && positions.contains(it)
    }.toSet()
}


/**
 * Delta positions surrounding a position. Filter out where all coordinates
 * are zero (this is the position itself
 */
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

/**
 * Take the set of surrounding deltas and map them all on the
 * positions, giving all the cells surrounding that position
 */
fun surroundingPositions(position: Position): Positions =
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
fun readFileToState(filename: String): Positions =
    File(filename).readLines()
        .mapIndexed { y, line ->
            line.mapIndexed { x, char -> if (char == '#') Position(x, y, 0) else null }
                .filterNotNull()
        }.flatten()
        .toSet()
