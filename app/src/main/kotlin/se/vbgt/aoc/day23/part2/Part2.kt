package se.vbgt.aoc.day23.part2

import se.vbgt.aoc.day23.common.createState
import se.vbgt.aoc.day23.common.moveCups

fun part2(numbers: List<Int>, times: Int): Long {
    // Due to what you can only assume is a mistranslation (you're not exactly fluent in Crab),
    // you are quite surprised when the crab starts arranging many cups in a circle on your
    // raft - one million (1000000) in total.
    val numbersMillion = numbers + (10..1_000_000)
    val startState = createState(numbersMillion)
    val finishState = moveCups(startState, times)

    val (n1, n2) = takeTwoAfterOne(finishState.circle)
    return n1.toLong() * n2.toLong()
}

/**
 * Determine which two cups will end up immediately clockwise of cup 1.
 * What do you get if you multiply their labels together?
 */
fun takeTwoAfterOne(circle: Map<Int, Int>): Pair<Int, Int> {
    val n1 = circle[1]!! // first clockwise of cup 1
    val n2 = circle[n1]!! // second clockwise of cup 1
    return Pair(n1, n2)
}

