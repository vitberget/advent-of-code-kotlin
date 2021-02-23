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
        // Before either player deals a card, if there was a previous round in this game that had  exactly the same
        // cards in the same order in the same players' decks, the game instantly ends in a win for player 1.
        if (history.contains(crabCombatState))
            return CrabCombatState(player1 = listOf(-1), player2 = listOf())
        history = history + crabCombatState

        val card1 = crabCombatState.player1[0]
        val card2 = crabCombatState.player2[0]

        // If both players have at least as many cards remaining in their deck as the value of the card
        // they just drew, the winner of the round is determined by playing a new game of Recursive Combat
        crabCombatState = if (card1 < crabCombatState.player1.size && card2 < crabCombatState.player2.size) {
            val subGameState = playCrabCombat(
                CrabCombatState(
                    player1 = crabCombatState.player1.drop(1).take(card1),
                    player2 = crabCombatState.player2.drop(1).take(card2)
                )
            )
            crabCombatState.nextState(player1isWinner = subGameState.player2.isNullOrEmpty())
        } else {
            // No special rules left...
            crabCombatState.nextState(player1isWinner = card1 > card2)
        }
    }

    return crabCombatState
}