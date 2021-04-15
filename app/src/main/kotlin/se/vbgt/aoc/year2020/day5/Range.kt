package se.vbgt.aoc.year2020.day5

data class Range(
    val lower: Int,
    val upper: Int,
) {
    fun lowerHalf(): Range = copy(
        upper = lower + (upper - lower) / 2
    )

    fun upperHalf(): Range = copy(
        lower = lower + (upper - lower) / 2 + 1
    )
}