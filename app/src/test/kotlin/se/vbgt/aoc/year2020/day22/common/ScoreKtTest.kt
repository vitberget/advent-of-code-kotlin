package se.vbgt.aoc.year2020.day22.common

import kotlin.test.Test
import kotlin.test.assertEquals

internal class ScoreKtTest {
    @Test
    fun `score example 1`() {
        assertEquals(306, scoreCrabDeck(listOf(3, 2, 10, 6, 8, 5, 9, 4, 7, 1)))
    }
}