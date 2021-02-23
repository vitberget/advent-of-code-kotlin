package se.vbgt.aoc.year2020.day23.common

data class State(
    // Current cup crab is handling
    val currentCup: Int,

    // The circle of cups: Keys in map points to next cup/number,
    // like a weird linked list/map hybrid...
    //
    // Much faster than a list when rearranging
    val cupCircle: HashMap<Int, Int>)

// puzzle "784235916" gives cupLabels [7, 8, 4, 2, 3, 5, 9, 1, 6] gives state:
// State(currentCup=7, cupCircle={7=8, 8=4, 4=2, 2=3, 3=5, 5=9, 9=1, 1=6, 6=7})

fun createState(cupLabels: List<Int>): State {
    // Make a copy of the list of numbers, with one number
    // taken from the head of the list,
    // added to the tail of the list
    val numbers2 = cupLabels.drop(1) + cupLabels.first()

    // ...so we can use zip() with offset numbers (offset by one)
    // to construct our initial circle of cups... map
    val cupCircleMap = cupLabels
        .zip(numbers2) { a, b -> a to b }
        .toMap()
        .toMutableMap() // Immutable map really slow... :(

    return State(
        cupCircle = cupCircleMap as HashMap<Int, Int>,

        // start with the first cup in the list of cups/numbers
        currentCup = cupLabels.first())
}