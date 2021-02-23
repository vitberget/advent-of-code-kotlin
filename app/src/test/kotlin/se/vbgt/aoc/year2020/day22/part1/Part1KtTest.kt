package se.vbgt.aoc.year2020.day22.part1

import se.vbgt.aoc.year2020.day22.common.CrabCombatState
import se.vbgt.aoc.year2020.day22.common.textToCrabCombatState
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Part1KtTest {
    @Test
    fun `test example play`() {
        assertEquals(
            CrabCombatState(
                player1 = listOf(),
                player2 = listOf(3, 2, 10, 6, 8, 5, 9, 4, 7, 1)
            ),
            playCrabCombat(
                textToCrabCombatState(
                    """
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
            """.trimIndent()
                )
            )
        )
    }
}