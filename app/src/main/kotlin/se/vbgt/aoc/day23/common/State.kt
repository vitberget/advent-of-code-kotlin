package se.vbgt.aoc.day23.common

data class State(
    // current cup crab is handling
    val current: Int,

    // the number circle: Keys in map points to value, like a weird list...
    // Much faster than a list when rearranging
    val circle: HashMap<Int, Int>)

fun createState(numbers: List<Int>): State {
    // Make a copy of the list of numbers, with one number
    // taken from the head of the list,
    // added to the tail of the list
    val numbers2 = numbers.drop(1) + numbers.first()

    // ...so we can use zip() with offset numbers (offset by one)
    // to construct our initial map-circle
    val circleMap = numbers
        .zip(numbers2) { a, b -> a to b }
        .toMap()
        .toMutableMap() // Immutable map really slow... :(

    return State(
        circle = circleMap as HashMap<Int, Int>,

        // start with the first cup in the list of numbers
        current = numbers.first())
}