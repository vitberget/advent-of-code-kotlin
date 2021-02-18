package se.vbgt.aoc.day23.common

fun moveCupsManyTimes(state: State, numberOfCrabMoves: Int): State {
    var iState = state
    for (i in 1..numberOfCrabMoves) {
        iState = moveCups(iState)
    }
    return iState
}

fun moveCups(state: State): State {
    // The crab picks up the three cups that are immediately clockwise of the current cup.
    // They are removed from the circle; cup spacing is adjusted as necessary to maintain the circle.
    val clockwiseCup1 = state.cupCircle[state.currentCup]!!
    val clockwiseCup2 = state.cupCircle[clockwiseCup1]!!
    val clockWiseCup3 = state.cupCircle[clockwiseCup2]!!

    val clockwiseOfTheThreeCups = state.cupCircle[clockWiseCup3]!!

    val destinationCup = calculateDestinationCup(
        currentCup = state.currentCup,
        threeClockwiseCups = setOf(clockwiseCup1, clockwiseCup2, clockWiseCup3),
        // largest cup label is the size of the circle, in both part1 and part2
        largestCupLabel = state.cupCircle.size
    )

    val destinationCupsOldTarget = state.cupCircle[destinationCup]!!

    // The crab places the cups it just picked up so that they are immediately clockwise of the
    // destination cup. They keep the same order as when they were picked up.

    // - current number should be pointing to what was right of the three cups
    state.cupCircle[state.currentCup] = clockwiseOfTheThreeCups
    // - destination cup should now point to the first clockwise cup
    state.cupCircle[destinationCup] = clockwiseCup1
    // - the last clockwise cup should now be pointing to what destination cup was pointing to
    state.cupCircle[clockWiseCup3] = destinationCupsOldTarget

    // The crab selects a new current cup: the cup which is immediately clockwise of the current cup.
    return state.copy(currentCup = clockwiseOfTheThreeCups)
}

/**
 * The crab selects a destination cup: the cup with a label equal to the current
 * cup's label minus one. If this would select one of the cups that was just picked
 * up, the crab will keep subtracting one until it finds a cup that wasn't just
 * picked up. If at any point in this process the value goes below the lowest value
 * on any cup's label, it wraps around to the highest value on any cup's label instead.
 */
fun calculateDestinationCup(currentCup: Int, threeClockwiseCups: Set<Int>, largestCupLabel: Int): Int {
    @Suppress("NAME_SHADOWING")
    //cups label minus one
    var currentCup = currentCup - 1

    while (true)
        when {
            // If at any point in this process the value goes below the lowest value
            // on any cup's label, it wraps around to the highest value on any cup's label instead.
            currentCup < 1 -> currentCup = largestCupLabel

            // If this would select one of the cups that was just picked up, the crab will keep
            // subtracting one until it finds a cup that wasn't just picked up.
            threeClockwiseCups.contains(currentCup) -> currentCup--

            // No more weird rules, return value
            else -> return currentCup
        }
}