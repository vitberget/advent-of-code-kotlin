package se.vbgt.aoc.year2020.day14

import kotlin.math.pow

tailrec fun part2(
    lines: List<String>,
    memory: MutableMap<Long, Long> = mutableMapOf(),
    maskOnes: Long = 0,
    maskXses: List<Int> = listOf(),
): Long =
    if (lines.isEmpty()) {
        memory.values.sumOf { it }
    } else {
        if (lines[0].startsWith(maskPrefix)) {
            val (ones, xses) = lineToMasks(lines[0])
            part2(
                lines.drop(1),
                memory,
                ones,
                xses
            )
        } else {
            val (mempos, value) = lineToMemposAndValue(lines[0])

            calcMemPos(mempos, maskOnes, maskXses)
                .forEach { memory[it] = value }

            part2(
                lines.drop(1),
                memory,
                maskOnes,
                maskXses
            )
        }
    }

fun calcMemPos(mempos: Long, maskOnes: Long, maskXses: List<Int>): Set<Long> {
    val memposOr = mempos.or(maskOnes)

    return (0 until (1L shl maskXses.size))
        .map { memPosModder(it, memposOr, maskXses) }
        .toSet()
}

fun memPosModder(number: Long, mempos: Long, maskXses: List<Int>): Long {
    val xVals = maskXses
        .mapIndexed { i, x -> x to 2.0.pow(i).toLong() }
        .map { (x, v) -> x to v.and(number) }

    val onesM = xVals
        .filterNot { it.second == 0L }
        .map { it.first }

    val zeroesM = xVals
        .filter { it.second == 0L }
        .map { it.first }

    val ones = onesM.sumOf { 1L shl it }
    val zeroes = zeroesM.sumOf { 1L shl it }.xor(Long.MAX_VALUE)

    return mempos or ones and zeroes
}

fun lineToMasks(line: String): Pair<Long, List<Int>> {
    val data = line.removePrefix(maskPrefix).reversed()

    val oneIdxs = indicesOf(data, '1')
    val ones = oneIdxs.sumOf { 1L shl it }

    val xIdxs = indicesOf(data, 'X')

    return ones to xIdxs
}
