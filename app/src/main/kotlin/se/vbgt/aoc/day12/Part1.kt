package se.vbgt.aoc.day12

import kotlin.math.abs

data class Ship(val x: Int, val y: Int, val rx: Int, val ry: Int, val heading: Heading)

fun main() {
    val ops = readFileIntoInstructions("puzzle.txt")
    part1(ops)
}

fun part1(ops: List<Action>) {
    val part1 = ops.fold(Ship(0, 0, 0, 0, Heading.EAST)) { ship, action ->
        doActionPart1(ship, action)
    }
    println(abs(part1.x) + abs(part1.y))
}

fun doActionPart1(ship: Ship, action: Action): Ship =
    when (action.type) {
        ActionType.NORTH -> north(ship, action.number)
        ActionType.SOUTH -> south(ship, action.number)
        ActionType.WEST -> west(ship, action.number)
        ActionType.EAST -> east(ship, action.number)
        ActionType.LEFT -> ship.copy(heading = turnLeft(ship.heading, action.number))
        ActionType.RIGHT -> ship.copy(heading = turnRight(ship.heading, action.number))
        ActionType.FORWARD -> when (ship.heading) {
            Heading.NORTH -> north(ship, action.number)
            Heading.SOUTH -> south(ship, action.number)
            Heading.EAST -> east(ship, action.number)
            Heading.WEST -> west(ship, action.number)
        }
    }

fun north(ship: Ship, number: Int) = ship.copy(y = ship.y - number)
fun south(ship: Ship, number: Int) = ship.copy(y = ship.y + number)
fun west(ship: Ship, number: Int) = ship.copy(x = ship.x - number)
fun east(ship: Ship, number: Int) = ship.copy(x = ship.x + number)