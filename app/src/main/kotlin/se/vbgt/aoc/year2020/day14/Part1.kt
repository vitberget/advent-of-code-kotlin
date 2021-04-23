package se.vbgt.aoc.year2020.day14

import kotlin.math.pow

val maskPrefix = "mask = "
val memposValueRegex = """^mem\[(\d+)\] = (\d+)""".toRegex()

tailrec fun part1(
    lines: List<String>,
    memory: Map<Long, Long> = mapOf(),
    maskOnes: Long = 0,
    maskZeroes: Long = 0
): Long =
    if (lines.isEmpty()) {
        memory.values.sumOf { it }
    } else {
        if (lines[0].startsWith(maskPrefix)) {
            val (ones, zeroes) = lineToMask(lines[0])
            part1(
                lines.drop(1),
                memory,
                ones,
                zeroes
            )
        } else {
            val (mempos, value) =
                memposValueRegex
                    .find(lines[0])!!
                    .groupValues
                    .drop(1)
                    .map { it.toLong() }
            val valueModded =
                value
                    .or(maskOnes)
                    .and(maskZeroes)

            part1(
                lines.drop(1),
                memory + (mempos to valueModded),
                maskOnes,
                maskZeroes
            )
        }
    }

fun lineToMask(line: String): Pair<Long, Long> {
    val data = line.removePrefix(maskPrefix).reversed()

    val oneIdxs = indicesOf(data, '1')
    val ones = indicesToLong(oneIdxs)

    val zeroIdxs = indicesOf(data, '0')
    val zeroes = Long.MAX_VALUE.xor(indicesToLong(zeroIdxs))

    return ones to zeroes
}

fun indicesToLong(idxs: List<Int>): Long =
    idxs.map { 2.0.pow(it).toLong() }
        .sumOf { it }

fun indicesOf(data: String, matchChar: Char): List<Int> =
    data.mapIndexed { i, c -> i to c }
        .filter { it.second == matchChar }
        .map { it.first }
