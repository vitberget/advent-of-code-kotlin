package se.vbgt.aoc.year2020.day22.part2

import se.vbgt.aoc.year2020.day22.common.CrabCombatState
import se.vbgt.aoc.year2020.day22.common.scoreCrabDeck

fun part2(crabCombatState: CrabCombatState): Int {
    val winningState = playCrabCombat(crabCombatState)
    return scoreCrabDeck(winningState.getWinningDeck())
}

fun playCrabCombat(crabCombatState: CrabCombatState): CrabCombatState {
    var currentRoundOfCrabCombat = crabCombatState

    var previoulyPlayedRounds = setOf<CrabCombatState>()

    while (!currentRoundOfCrabCombat.hasAWinner()) {
        // Before either player deals a card, if there was a previous round in this game that had  exactly the same
        // cards in the same order in the same players' decks, the game instantly ends in a win for player 1.
        if (previoulyPlayedRounds.contains(currentRoundOfCrabCombat))
            return CrabCombatState(player1 = listOf(-1), player2 = listOf())
        previoulyPlayedRounds = previoulyPlayedRounds + currentRoundOfCrabCombat

        val playerCard1 = currentRoundOfCrabCombat.player1[0]
        val playerCard2 = currentRoundOfCrabCombat.player2[0]

        // If both players have at least as many cards remaining in their deck as the value of the card
        // they just drew, the winner of the round is determined by playing a new game of Recursive Combat
        currentRoundOfCrabCombat =
            if (playerCard1 < currentRoundOfCrabCombat.player1.size && playerCard2 < currentRoundOfCrabCombat.player2.size) {
                val subGameState = playCrabCombat(
                    CrabCombatState(
                        player1 = currentRoundOfCrabCombat.player1.drop(1).take(playerCard1),
                        player2 = currentRoundOfCrabCombat.player2.drop(1).take(playerCard2)
                    )
                )
                currentRoundOfCrabCombat.nextState(player1isWinner = subGameState.player2.isNullOrEmpty())
            } else {
                // No special rules left...
                currentRoundOfCrabCombat.nextState(player1isWinner = playerCard1 > playerCard2)
            }
    }

    return currentRoundOfCrabCombat
}