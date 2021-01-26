package se.vbgt.aoc.day12

enum class Heading {
    NORTH,
    EAST,
    SOUTH,
    WEST
}

fun turnLeft(heading: Heading, degrees: Int): Heading =
    if (degrees == 0) {
        heading
    } else {
        turnLeft(
            when (heading) {
                Heading.NORTH -> Heading.WEST
                Heading.SOUTH -> Heading.EAST
                Heading.WEST -> Heading.SOUTH
                Heading.EAST -> Heading.NORTH
            },
            degrees - 90
        )
    }


fun turnRight(heading: Heading, degrees: Int): Heading =
    if (degrees == 0) {
        heading
    } else {
        turnRight(
            when (heading) {
                Heading.NORTH -> Heading.EAST
                Heading.SOUTH -> Heading.WEST
                Heading.WEST -> Heading.NORTH
                Heading.EAST -> Heading.SOUTH
            },
            degrees - 90
        )
    }