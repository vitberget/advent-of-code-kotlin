package se.vbgt.aoc.day23.common


fun moveCups(state: State, times: Int): State {
    var iState = state
    for (i in 1..times) {
        iState = moveCups(iState)
    }
    return iState
}

fun moveCups(state: State): State {
    // The crab picks up the three cups that are immediately clockwise of the current cup.
    // They are removed from the circle; cup spacing is adjusted as necessary to maintain the circle.
    val clockwiseCup1 = state.circle[state.current]!!
    val clockwiseCup2 = state.circle[clockwiseCup1]!!
    val clockWiseCup3 = state.circle[clockwiseCup2]!!

    val rightOfClockwiseCups = state.circle[clockWiseCup3]!!

    val destinationCup = destinationCup(
        state.current,
        setOf(clockwiseCup1, clockwiseCup2, clockWiseCup3),
        state.circle.size
    )

    val destinationCupsOldPointer = state.circle[destinationCup]!!

    // The crab places the cups it just picked up so that they are immediately clockwise of the
    // destination cup. They keep the same order as when they were picked up.

    // - current number should be pointing to what was right of the three cups
    state.circle[state.current] = rightOfClockwiseCups
    // - destination cup should now point to the first clockwise cup
    state.circle[destinationCup] = clockwiseCup1
    // - the last clockwise cup should now be pointing to what destination cup was pointing to
    state.circle[clockWiseCup3] = destinationCupsOldPointer

    // The crab selects a new current cup: the cup which is immediately clockwise of the current cup.
    return state.copy(current = state.circle[state.current]!!)
}

/**
 * The crab selects a destination cup: the cup with a label equal to the current
 * cup's label minus one. If this would select one of the cups that was just picked
 * up, the crab will keep subtracting one until it finds a cup that wasn't just
 * picked up. If at any point in this process the value goes below the lowest value
 * on any cup's label, it wraps around to the highest value on any cup's label instead.
 */
fun destinationCup(current: Int, three: Set<Int>, circleSize: Int): Int {
    @Suppress("NAME_SHADOWING")
    //cups label minus one
    var current = current - 1

    while (true)
        when {
            // If at any point in this process the value goes below the lowest value
            // on any cup's label, it wraps around to the highest value on any cup's label instead.
            current < 1 -> current = circleSize

            // If this would select one of the cups that was just picked up, the crab will keep
            // subtracting one until it finds a cup that wasn't just picked up.
            three.contains(current) -> current--

            // No more weird rules, return value
            else -> return current
        }
}