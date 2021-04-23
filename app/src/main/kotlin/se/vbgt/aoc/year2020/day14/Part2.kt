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
            val (mempos, value) =
                memposValueRegex
                    .find(lines[0])!!
                    .groupValues
                    .drop(1)
                    .map { it.toLong() }

            val memPositions = calcMemPos(mempos, maskOnes, maskXses)

            part2(
                lines.drop(1),
                updateMemory(memory, memPositions.toList(), value),
                maskOnes,
                maskXses
            )
        }
    }

tailrec fun updateMemory(
    memory: MutableMap<Long, Long>,
    memPositions: List<Long>,
    value: Long
): MutableMap<Long, Long> =
    if (memPositions.isEmpty()) {
        memory
    } else {
        memory.put(memPositions[0], value)
        updateMemory(
            memory,
            memPositions.drop(1),
            value
        )
    }

fun calcMemPos(mempos: Long, maskOnes: Long, maskXses: List<Int>): Set<Long> {

    val memposOr = mempos.or(maskOnes)
    val upperLimit = 2.0
        .pow(maskXses.size)
        .toInt()
        .dec()

    return (0..upperLimit)
        .map { it.toLong() }
        .map { memPosModder(it, memposOr, maskXses) }
        .toSet()

}

fun memPosModder(number: Long, mempos: Long, maskXses: List<Int>): Long {
    val xVals = maskXses
        .mapIndexed { i, x -> x to 2.0.pow(i).toLong() }
        .map { (x, v) -> x to v.and(number) }

    val onesM = xVals
        .filterNot { (_, v) -> v == 0L }
        .map { it.first }

    val zeroesM = xVals
        .filter { (_, v) -> v == 0L }
        .map { it.first }

    val ones = indicesToLong(onesM)
    val zeroes = indicesToLong(zeroesM).xor(Long.MAX_VALUE)

    return mempos.or(ones).and(zeroes)
}


fun lineToMasks(line: String): Pair<Long, List<Int>> {
    val data = line.removePrefix(maskPrefix).reversed()

    val oneIdxs = indicesOf(data, '1')
    val ones = indicesToLong(oneIdxs)

    val xIdxs = indicesOf(data, 'X')

    return ones to xIdxs
}
