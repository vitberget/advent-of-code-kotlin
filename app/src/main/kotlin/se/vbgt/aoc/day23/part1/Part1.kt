package se.vbgt.aoc.day23.part1

import se.vbgt.aoc.day23.common.createState
import se.vbgt.aoc.day23.common.moveCupsManyTimes

fun part1(cupLabels: List<Int>, numberOfCrabMoves: Int): String {
    var state = createState(cupLabels)
   
    state = moveCupsManyTimes(state, numberOfCrabMoves)
   
    val resultNumbers = doThatOneThinge(state.cupCircle)

    return resultNumbers.joinToString(separator = "")
}

/**
 * After the crab is done, what order will the cups be in? Starting after the cup labeled 1,
 * collect the other cups' labels clockwise into a single string with no extra characters;
 * each number except 1 should appear exactly once.
 */
private fun doThatOneThinge(state: Map<Int, Int>): List<Int> {
    val result = mutableListOf<Int>()

    // get whatever the key 1 is pointing to
    var current = state[1]!!

    while (current != 1) { // when we hit 1, we looped around the circle
        result.add(current)
        current = state[current]!!
    }

    return result
}

