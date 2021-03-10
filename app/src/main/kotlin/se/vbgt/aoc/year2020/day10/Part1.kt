package se.vbgt.aoc.year2020.day10

fun part1(adapters: Adapters): Long {
    val deltas = calculateDeltasBetweenValues(adapters)

    val numberOfGapsSize1 = deltas.count { it == 1L }.toLong()
    val numberOfGapsSize3 = deltas.count { it == 3L }.toLong()

    return numberOfGapsSize1 * numberOfGapsSize3
}

private fun calculateDeltasBetweenValues(adapters: Adapters): Adapters {
    // From the incoming list "1 2 3 4 7", drop first respectively the last
    val list1 = adapters.drop(1)  //2 3 4 7
    val list2 = adapters.dropLast(1) // 1 2 3 4

    // zip the lists, subtracting between the values in the list
    // 2 - 1 = 1
    // 3 - 2 = 1
    // 4 - 3 = 1
    // 7 - 4 = 3
    return list1.zip(list2) { n1, n2 -> n1 - n2 }
}

