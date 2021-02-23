package se.vbgt.aoc.year2020.day23.common

import kotlin.test.Test
import kotlin.test.assertEquals

internal class CupActionKtTest {

    private val move1 = createState(listOf(3, 8, 9, 1, 2, 5, 4, 6, 7))
    private val move2 = createState(listOf(2, 8, 9, 1, 5, 4, 6, 7, 3))
    private val move3 = createState(listOf(5, 4, 6, 7, 8, 9, 1, 3, 2))
    private val move4 = createState(listOf(8, 9, 1, 3, 4, 6, 7, 2, 5))
    private val move5 = createState(listOf(4, 6, 7, 9, 1, 3, 2, 5, 8))
    private val move6 = createState(listOf(1, 3, 6, 7, 9, 2, 5, 8, 4))
    private val move7 = createState(listOf(9, 3, 6, 7, 2, 5, 8, 4, 1))
    private val move8 = createState(listOf(2, 5, 8, 3, 6, 7, 4, 1, 9))
    private val move9 = createState(listOf(6, 7, 4, 1, 5, 8, 3, 9, 2))
    private val move10 = createState(listOf(5, 7, 4, 1, 8, 3, 9, 2, 6))
    private val final = createState(listOf(8, 3, 7, 4, 1, 9, 2, 6, 5))

    @Test
    fun `test moveCups singles`() {

        assertEquals(move2, moveCups(move1))
        assertEquals(move3, moveCups(move2))
        assertEquals(move4, moveCups(move3))
        assertEquals(move5, moveCups(move4))
        assertEquals(move6, moveCups(move5))
        assertEquals(move7, moveCups(move6))
        assertEquals(move8, moveCups(move7))
        assertEquals(move9, moveCups(move8))
        assertEquals(move10, moveCups(move9))
        assertEquals(final, moveCups(move10))
    }

    @Test
    fun `test moveCups n times`() {
        assertEquals(final, moveCupsManyTimes(move1, 10))
    }

    @Test
    fun `test destinationCup`() {
        assertEquals(calculateDestinationCup(3, setOf(8, 9, 1), 9), 2)
        assertEquals(calculateDestinationCup(2, setOf(8, 9, 1), 9), 7)
        assertEquals(calculateDestinationCup(5, setOf(4, 6, 7), 9), 3)
        assertEquals(calculateDestinationCup(4, setOf(6, 7, 9), 9), 3)
        assertEquals(calculateDestinationCup(1, setOf(3, 6, 7), 9), 9)
        assertEquals(calculateDestinationCup(5, setOf(7, 4, 1), 9), 3)

    }
}