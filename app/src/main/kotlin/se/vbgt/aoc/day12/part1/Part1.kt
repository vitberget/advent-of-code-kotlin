package se.vbgt.aoc.day12.part1

import se.vbgt.aoc.day12.common.Action
import se.vbgt.aoc.day12.common.ActionType
import kotlin.math.abs

data class Ship(
    val x: Int,
    val y: Int,
    val heading: Heading
) {
    fun north(number: Int) = copy(y = y - number)
    fun south(number: Int) = copy(y = y + number)
    fun west(number: Int) = copy(x = x - number)
    fun east(number: Int) = copy(x = x + number)

    fun turnLeft(number: Int): Ship =
        if (number == 0) this
        else copy(
            heading = when (heading) {
                Heading.NORTH -> Heading.WEST
                Heading.SOUTH -> Heading.EAST
                Heading.WEST -> Heading.SOUTH
                Heading.EAST -> Heading.NORTH
            }
        ).turnLeft(number - 90)

    fun turnRight(number: Int): Ship =
        if (number == 0) this
        else copy(
            heading = when (heading) {
                Heading.NORTH -> Heading.EAST
                Heading.SOUTH -> Heading.WEST
                Heading.WEST -> Heading.NORTH
                Heading.EAST -> Heading.SOUTH
            }
        ).turnRight(number - 90)

    fun forward(number: Int): Ship =
        when (heading) {
            Heading.NORTH -> north(number)
            Heading.SOUTH -> south(number)
            Heading.EAST -> east(number)
            Heading.WEST -> west(number)
        }

    fun manhattan(): Int = abs(x) + abs(y)
}

fun part1(actions: List<Action>) {
    val endingShip = actions.fold(Ship(0, 0, Heading.EAST)) { ship, action ->
        when (action.type) {
            ActionType.NORTH -> ship.north(action.number)
            ActionType.SOUTH -> ship.south(action.number)
            ActionType.WEST -> ship.west(action.number)
            ActionType.EAST -> ship.east(action.number)
            ActionType.LEFT -> ship.turnLeft(action.number)
            ActionType.RIGHT -> ship.turnRight(action.number)
            ActionType.FORWARD -> ship.forward(action.number)
        }
    }

    println("Part 1: ${endingShip.manhattan()}")
}