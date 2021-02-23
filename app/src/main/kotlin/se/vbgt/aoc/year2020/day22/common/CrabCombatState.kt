package se.vbgt.aoc.year2020.day22.common

import java.io.File

typealias CrabDeck = List<Int>

data class CrabCombatState(
    val player1: CrabDeck,
    val player2: CrabDeck
)

fun fileToCrabCombatState(filename: String): CrabCombatState =
    textToCrabCombatState(
        File(filename).readText()
    )

fun textToCrabCombatState(text: String): CrabCombatState =
    with(
        text.split("\n\n")
            .map { textToCrabDeck(it) }
    ) {
        CrabCombatState(
            player1 = this[0],
            player2 = this[1]
        )
    }

fun textToCrabDeck(text: String): CrabDeck =
    text.lines()
        .drop(1)
        .map { it.toInt() }
