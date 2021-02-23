package se.vbgt.aoc.year2020.day22.part2

import se.vbgt.aoc.year2020.day22.common.CrabCombatState
import se.vbgt.aoc.year2020.day22.common.textToCrabCombatState
import kotlin.test.Test
import kotlin.test.assertEquals

internal class Part2KtTest {
    @Test
    fun `example part 2`() {
        assertEquals(
            CrabCombatState(
                player1 = listOf(),
                player2 = listOf(7, 5, 6, 2, 4, 1, 10, 8, 9, 3)
            ),
            playCrabCombat(textToCrabCombatState("""
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
            """.trimIndent()))
        )
    }
}