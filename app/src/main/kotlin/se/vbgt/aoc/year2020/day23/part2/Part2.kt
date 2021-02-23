package se.vbgt.aoc.year2020.day23.part2

import se.vbgt.aoc.year2020.day23.common.createState
import se.vbgt.aoc.year2020.day23.common.moveCupsManyTimes

fun part2(cupLabels: List<Int>, numberOfCrabMoves: Int): Long {
    println("===== Part2 ====")
    // Due to what you can only assume is a mistranslation (you're not exactly fluent in Crab),
    // you are quite surprised when the crab starts arranging many cups in a circle on your
    // raft - one million (1000000) in total.
    val oneMillionCups = cupLabels + (10..1_000_000)
    
    val startState = createState(oneMillionCups)
    
    val endState = moveCupsManyTimes(startState, numberOfCrabMoves)

    val (cup1, cup2) = takeTwoAfterCupOne(endState.cupCircle)
    return cup1.toLong() * cup2.toLong()
}

/**
 * Determine which two cups will end up immediately clockwise of cup 1.
 * What do you get if you multiply their labels together?
 */
fun takeTwoAfterCupOne(cupCircle: Map<Int, Int>): Pair<Int, Int> {
    val clockwiseCup1 = cupCircle[1]!! // first clockwise of cup 1
    val clockwiseCup2 = cupCircle[clockwiseCup1]!! // second clockwise of cup 1
    return Pair(clockwiseCup1, clockwiseCup2)
}

