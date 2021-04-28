package se.vbgt.aoc.year2020.day14

tailrec fun part2(
    lines: List<String>,
    memory: MutableMap<Long, Long> = mutableMapOf(),
    maskOnes: Long = 0L,
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
            lineToMemposAndValue(lines[0]).apply {
                calcMemPos(this[0], maskOnes, maskXses)
                    .forEach { memory[it] = this[1] }
            }

            part2(
                lines.drop(1),
                memory,
                maskOnes,
                maskXses
            )
        }
    }

fun calcMemPos(mempos: Long, maskOnes: Long, maskXses: List<Int>): Set<Long> =
    (0 until (1L shl maskXses.size))
        .map { memPosModder(it, mempos or maskOnes, maskXses) }
        .toSet()

fun memPosModder(number: Long, mempos: Long, maskXses: List<Int>): Long {
    maskXses
        .mapIndexed { i, x -> x to (1L shl i) }
        .map { (x, v) -> x to (v and number) }
        .apply {
            val ones = this
                .filterNot { it.second == 0L }
                .map { it.first }
                .indicesToLong()

            val zeroes = this
                .filter { it.second == 0L }
                .map { it.first }
                .indicesToLong()
                .inv()

            return mempos or ones and zeroes
        }
}

fun lineToMasks(line: String): Pair<Long, List<Int>> {
    line.removePrefix(maskPrefix)
        .reversed()
        .apply {
            val ones = this
                .indicesOf('1')
                .sumOf { 1L shl it }

            val xIdxs = this.indicesOf('X')

            return ones to xIdxs
        }
}
