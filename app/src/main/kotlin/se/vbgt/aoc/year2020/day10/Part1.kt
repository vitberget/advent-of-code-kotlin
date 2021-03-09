package se.vbgt.aoc.year2020.day10

fun part1(adapters: Adapters): Long {
    val deltas = calculateDeltasBetweenValues(adapters)

    val numberOfGapsSize1 = deltas.count { it == 1L }.toLong()
    val numberOfGapsSize3 = deltas.count { it == 3L }.toLong()

    return numberOfGapsSize1 * numberOfGapsSize3
}

private fun calculateDeltasBetweenValues(adapters: Adapters): Adapters {
    val list1 = adapters.drop(1)
    val list2 = adapters.dropLast(1)
    return list1.zip(list2) { n1, n2 -> n1 - n2 }
}

