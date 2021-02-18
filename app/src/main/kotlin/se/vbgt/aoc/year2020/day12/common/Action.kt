package se.vbgt.aoc.year2020.day12.common

import se.vbgt.aoc.year2020.day12.common.ActionType.*

enum class ActionType {
    FORWARD,
    LEFT,
    RIGHT,
    NORTH,
    SOUTH,
    EAST,
    WEST;
}
fun actionFromLetter(letter: Char) : ActionType =
    when(letter) {
        'L' -> LEFT
        'R' -> RIGHT
        'F' -> FORWARD
        'N' -> NORTH
        'S' -> SOUTH
        'W' -> WEST
        'E' -> EAST
        else -> throw IllegalArgumentException("Not a correct ActionType letter")
    }

data class Action(val type: ActionType, val number: Int)