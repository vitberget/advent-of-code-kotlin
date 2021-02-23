package se.vbgt.aoc.year2020.day22.common

import kotlin.test.Test
import kotlin.test.assertEquals

internal class CrabCombatStateKtTest {
    @Test
    fun `example state`() {
        assertEquals(
            CrabCombatState(
                player1 = listOf(9, 2, 6, 3 ,1),
                player2 = listOf(5, 8, 4, 7, 10)
            ),
            textToCrabCombatState("""
               Player 1:
               9
               2
               6
               3
               1

               Player 2:
               5
               8
               4
               7
               10
            """.trimIndent())
        )
    }

}