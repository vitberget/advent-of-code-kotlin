package se.vbgt.aoc.year2020.day14

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
            val (mempos, value) = lineToMemposAndValue(lines[0])
            val valueModded = value or maskOnes and maskZeroes

            part1(
                lines.drop(1),
                memory + (mempos to valueModded),
                maskOnes,
                maskZeroes
            )
        }
    }

fun lineToMemposAndValue(line: String) =
    memposValueRegex
        .find(line)!!
        .groupValues
        .drop(1)
        .map { it.toLong() }

val memposValueRegex = """^mem\[(\d+)\] = (\d+)""".toRegex()
const val maskPrefix = "mask = "

fun lineToMask(line: String): Pair<Long, Long> {
    val data = line.removePrefix(maskPrefix).reversed()

    val oneIdxs = indicesOf(data, '1')
    val ones = oneIdxs.sumOf { 1L shl it }

    val zeroes = indicesOf(data, '0')
        .sumOf { 1L shl it }
        .xor(Long.MAX_VALUE)

    return ones to zeroes
}

fun indicesOf(data: String, matchChar: Char): List<Int> =
    data.mapIndexed { i, c -> i to c }
        .filter { it.second == matchChar }
        .map { it.first }
