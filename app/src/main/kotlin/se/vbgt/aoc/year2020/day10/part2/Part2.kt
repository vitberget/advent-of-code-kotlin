package se.vbgt.aoc.year2020.day10.part2

fun part2(adapters: List<Long>): Long =
    countPossibleJumps(
        calculateNumberOfPossibleJumpsForAllAdapters(
            listOf(0L) + adapters + (adapters.maxOfOrNull { it }!! + 3)
        ),
        0,
        mutableMapOf()
    )

private fun countPossibleJumps(
    jumps: Map<Long, Set<Long>>,
    from: Long,
    // A lot of jumps are calculated a very large amount of times,
    // memorizing the calculations saves alot of time
    memoizeCache: MutableMap<Long, Long>
): Long =
    memoizeCache.getOrPut(from) {
        with(jumps[from]) {
            if (this == null || this.isEmpty())
                1L
            else
                this
                    .map { countPossibleJumps(jumps, it, memoizeCache) }
                    .sumOf { it }
        }
    }

private fun calculateNumberOfPossibleJumpsForAllAdapters(adapters: List<Long>): Map<Long, Set<Long>> =
    calculateNumberOfPossibleJumpsFrom(
        adapters,
        mutableMapOf()
    )

private tailrec fun calculateNumberOfPossibleJumpsFrom(
    adapters: List<Long>,
    numberMap: MutableMap<Long, Set<Long>>
): Map<Long, Set<Long>> =
    if (adapters.isEmpty())
        numberMap
    else {
        numberMap.addPossibleJumpsFromHere(adapters)
        calculateNumberOfPossibleJumpsFrom(adapters.drop(1), numberMap)
    }

private fun MutableMap<Long, Set<Long>>.addPossibleJumpsFromHere(adapters: List<Long>) {
    val adapter = adapters[0]
    this[adapter] = adapters
        .drop(1)
        .takeWhile { n -> n - adapter < 4 }
        .toSet()
}