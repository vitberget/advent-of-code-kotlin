package se.vbgt.aoc.year2020.day22.common

/**
 * Once the game ends, you can calculate the winning player's score. The bottom card
 * in their deck is worth the value of the card multiplied by 1,
 * the second-from-the-bottom card is worth the value of the card multiplied by 2,
 * and so on. With 10 cards, the top card is worth the value on the card multiplied by 10.
 */
fun scoreCrabDeck(crabDeck: CrabDeck): Int =
    crabDeck
        .reversed()
        .mapIndexed { i, v -> (i + 1) * v }
        .sum()