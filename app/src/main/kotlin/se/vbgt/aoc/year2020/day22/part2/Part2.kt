package se.vbgt.aoc.year2020.day22.part2

import se.vbgt.aoc.year2020.day22.common.CrabCombatState
import se.vbgt.aoc.year2020.day22.common.scoreCrabDeck

fun part2(crabCombatState: CrabCombatState): Int {
    val winningState = playCrabCombat(crabCombatState)
    return scoreCrabDeck(winningState.getWinningDeck())
}

fun playCrabCombat(crabCombatState: CrabCombatState): CrabCombatState {
    @Suppress("NAME_SHADOWING")
    var crabCombatState = crabCombatState

    var history = setOf<CrabCombatState>()

    while (!crabCombatState.hasAWinner()) {
        if (history.contains(crabCombatState))
            return CrabCombatState(player1 = listOf(-1), player2 = listOf())

        history = history + crabCombatState

        val card1 = crabCombatState.player1[0]
        val card2 = crabCombatState.player2[0]

        crabCombatState = if (card1 < crabCombatState.player1.size && card2 < crabCombatState.player2.size) {
            val subGameState = playCrabCombat(
                CrabCombatState(
                    player1 = crabCombatState.player1.drop(1).take(card1),
                    player2 = crabCombatState.player2.drop(1).take(card2)
                )
            )
            crabCombatState.nextState(player1isWinner = subGameState.player2.isNullOrEmpty())
        } else {
            crabCombatState.nextState(player1isWinner = card1 > card2)
        }
    }

    return crabCombatState
}