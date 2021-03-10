package se.vbgt.aoc.year2020.day10

typealias AdapterJumpMap = Map<Adapter, Set<Adapter>>

fun part2(adapters: Adapters): Long {
    val memoizeCache = mutableMapOf<Adapter, Long>()
    val startingAdapter: Long = 0

    return countPossibleJumps(
        calculateJumpsForAllAdapters(adapters),
        startingAdapter,
        memoizeCache
    )
}

/**
 * Each adapter has one to three possible jump points. (Except last adapter, the target)
 * For each possible branch (jump point) calculate the number of possible jump points forward in
 * that branch.
 *
 * Memoize it, becuase we will hit the same adapter multiple times, since it they are reachable
 * "sideways" in the branches. Saves A LOT of time.
 */
private fun countPossibleJumps(
    jumps: AdapterJumpMap,
    from: Adapter,
    memoizeCache: MutableMap<Adapter, Long>
): Long =
    memoizeCache.getOrPut(from) {
        with(jumps[from]) {
            if (this == null || this.isEmpty()) // Last jump, still counts
                1L
            else
                this
                    // for each possible branch...
                    .map { countPossibleJumps(jumps, it, memoizeCache) }
                    .sumOf { it }
        }
    }

/**
 * Create a map of all possible jump. From each adapter to the possible
 * jump points within reach of that adapter (forward).
 */
private fun calculateJumpsForAllAdapters(adapters: Adapters): AdapterJumpMap =
    calculatePossibleJumpsFrom(
        adapters,
        mutableMapOf()
    )

/**
 * The first number in the list is the current adapter processed.
 * Calculate possible jump point, then recur with the next number (by placing
 * this number first in the list).
 */
private tailrec fun calculatePossibleJumpsFrom(
    adapters: Adapters,
    numberMap: MutableMap<Adapter, Set<Adapter>>
): AdapterJumpMap =
    if (adapters.isEmpty())
        numberMap
    else {
        numberMap.addPossibleJumpsFromHere(adapters)
        calculatePossibleJumpsFrom(adapters.drop(1), numberMap)
    }

/**
 * The possible jumps from a positions (first in the list) is to all adapters where the
 * jump distance is less or equal to 3.
 */
private fun MutableMap<Adapter, Set<Adapter>>.addPossibleJumpsFromHere(adapters: Adapters) {
    val adapter = adapters[0]
    this[adapter] = adapters
        .drop(1)
        .takeWhile { n -> n <= adapter + 3 }
        .toSet()
}