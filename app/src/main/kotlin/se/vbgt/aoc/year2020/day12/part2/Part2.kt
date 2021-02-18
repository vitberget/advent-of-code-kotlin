package se.vbgt.aoc.year2020.day12.part2

import se.vbgt.aoc.year2020.day12.common.*
import se.vbgt.aoc.year2020.day12.common.ActionType.*
import kotlin.math.abs

data class Ship(
    val x: Int = 0,
    val y: Int = 0,
    val wx: Int = 0,
    val wy: Int = 0
) {
    fun north(number: Int): Ship = copy(wy = wy - number)
    fun south(number: Int): Ship = copy(wy = wy + number)
    fun west(number: Int): Ship = copy(wx = wx - number)
    fun east(number: Int): Ship = copy(wx = wx + number)

    fun turnRight(number: Int): Ship =
        if (number == 0) this
        else copy(wx = -wy, wy = wx).turnRight(number - 90)

    fun turnLeft(number: Int): Ship =
        if (number == 0) this
        else copy(wx = wy, wy = -wx).turnLeft(number - 90)

    fun forward(number: Int): Ship = copy(
        x = x + wx * number,
        y = y + wy * number
    )

    fun manhattan(): Int = abs(x) + abs(y)
}

fun day12part2(actions: List<Action>) {
    val startingShip = Ship()
        .east(10)
        .north(1)

    val endingShip = actions.fold(startingShip) { ship, (type, number) ->
        when (type) {
            NORTH -> ship.north(number)
            SOUTH -> ship.south(number)
            WEST -> ship.west(number)
            EAST -> ship.east(number)
            LEFT -> ship.turnLeft(number)
            RIGHT -> ship.turnRight(number)
            FORWARD -> ship.forward(number)
        }
    }

    println("Part 2: ${endingShip.manhattan()}")
}

