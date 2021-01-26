package se.vbgt.aoc.day12

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
        'L' -> ActionType.LEFT
        'R' -> ActionType.RIGHT
        'F' -> ActionType.FORWARD
        'N' -> ActionType.NORTH
        'S' -> ActionType.SOUTH
        'W' -> ActionType.WEST
        'E' -> ActionType.EAST
        else -> throw IllegalArgumentException("Not a correct ActionType letter")
    }

data class Action(val type: ActionType, val number: Int)