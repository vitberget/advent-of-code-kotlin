package se.vbgt.aoc.year2020.day22.part1

import se.vbgt.aoc.year2020.day22.common.CrabCombatState
import se.vbgt.aoc.year2020.day22.common.scoreCrabDeck

fun part1(crabCombatState: CrabCombatState): Int {
    val winningState = playCrabCombat(crabCombatState)
    val winningDeck = listOf(winningState.player1, winningState.player2)
        .first { it.isNotEmpty() }
    return scoreCrabDeck(winningDeck)
}

/**
 * Before the game starts, split the cards so each player has their own deck
 * (your puzzle input). Then, the game consists of a series of rounds: both players
 * draw their top card, and the player with the higher-valued card wins the round.
 * The winner keeps both cards, placing them on the bottom of their own deck so
 * that the winner's card is above the other card. If this causes a player to
 * have all of the cards, they win, and the game ends.
 */
tailrec fun playCrabCombat(crabCombatState: CrabCombatState): CrabCombatState =
    when {
        // If this causes a player to have all of the cards, they win, and the game ends
        crabCombatState.player1.isEmpty() -> crabCombatState
        crabCombatState.player2.isEmpty() -> crabCombatState

        else -> {
            val crabCard1 = crabCombatState.player1[0]
            val crabCard2 = crabCombatState.player2[0]

            // both players draw their top card, and the player with the higher-valued card wins the round. The winner
            // keeps both cards, placing them on the bottom of their own deck so that the winner's card is above the
            // other card.
            playCrabCombat(
                if (crabCard1 > crabCard2)
                    crabCombatState.copy(
                        player1 = crabCombatState.player1.drop(1) + crabCard1 + crabCard2,
                        player2 = crabCombatState.player2.drop(1)
                    )
                else
                    crabCombatState.copy(
                        player1 = crabCombatState.player1.drop(1),
                        player2 = crabCombatState.player2.drop(1) + crabCard2 + crabCard1

                    )
            )
        }
    }
