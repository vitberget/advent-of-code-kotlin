package se.vbgt.aoc.day15

fun main() {
    val input = "14,8,16,0,1,17"
    var state = stringToState(input)

    state = getTurn(state, 2020)
    println("Part 1: ${state.number}")

    state = getTurn(state, 30000000)
    println("Part 2: ${state.number}")
}

data class State(
    val number: Int,
    val index: Int,
    val wordMap: MutableMap<Int, Int> // Using (immutable) Map, to slow
)

fun stringToState(string: String): State {
    val numbers = string.split(",")
        .map { it.toInt() }

    return State(
        index = numbers.size - 1,
        number = numbers.last(),
        wordMap = numbers.dropLast(1)
            .mapIndexed { i, v -> v to i }
            .toMap()
            .toMutableMap())
}

private fun getTurn(state: State, targetTurn: Int): State {
    var workState = state

    while (workState.index < targetTurn - 1) {
        val number = calculateNextNumber(workState)

        workState.wordMap[workState.number] = workState.index

        workState = workState.copy(
            number = number,
            index = workState.index + 1
        )
    }
    return workState
}

private fun calculateNextNumber(workState: State): Int =
    with(workState.wordMap[workState.number]) {
        if (this == null) 0 else workState.index - this
    }