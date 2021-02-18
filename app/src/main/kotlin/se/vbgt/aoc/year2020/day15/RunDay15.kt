package se.vbgt.aoc.year2020.day15

import kotlin.system.measureTimeMillis

fun main() {
    val input = "14,8,16,0,1,17"
    var state = stringToState(input)

    val time1 = measureTimeMillis {
        state = getTurn(state, 2020)
    }
    println("Part 1: ${state.number}, ${time1}ms")

    val time2 = measureTimeMillis {
        state = getTurn(state, 30_000_000)
    }
    println("Part 2: ${state.number}, ${time2}ms")
}


data class State(
    val number: Int,
    val index: Int,
    val wordMap: MutableMap<Int, Int> // Using (immutable) Map, to slow
)

fun stringToState(string: String): State =
    with(string.split(",").map { it.toInt() }) {
        State(
            index = this.size - 1,
            number = this.last(),
            wordMap = this
                .dropLast(1)
                .mapIndexed { i, v -> v to i }
                .toMap()
                .toMutableMap())
    }

private fun getTurn(state: State, targetTurn: Int): State {
    @Suppress("NAME_SHADOWING")
    var state = state

    while (state.index < targetTurn - 1)
        state = state.copy(
            number = state.wordMap
                .put(state.number, state.index)
                .getSpokenWord(state.index),
            index = state.index + 1
        )

    return state
}

private fun Int?.getSpokenWord(index: Int): Int =
    if (this == null) 0
    else index - this