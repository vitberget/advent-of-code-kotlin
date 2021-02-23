package se.vbgt.aoc.year2020.day22.common

import java.io.File

typealias CrabDeck = List<Int>

data class CrabCombatState(
    val player1: CrabDeck,
    val player2: CrabDeck
) {
    fun nextState(player1isWinner: Boolean): CrabCombatState =
        if (player1isWinner)
            copy(
                player1 = player1.drop(1) + player1[0] + player2[0],
                player2 = player2.drop(1)
            )
        else
            copy(
                player1 = player1.drop(1),
                player2 = player2.drop(1) + player2[0] + player1[0]
            )

    fun hasAWinner(): Boolean = player1.isEmpty() || player2.isEmpty()

    fun getWinningDeck(): CrabDeck =
        listOf(player1, player2)
            .first { it.isNotEmpty() }
}

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
        .mapNotNull { it.toIntOrNull() }
