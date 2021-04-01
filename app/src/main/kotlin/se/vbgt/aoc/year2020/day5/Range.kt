package se.vbgt.aoc.year2020.day5

data class Range(
    val lower: Int,
    val higher: Int,
) {
    fun lowerHalf(): Range = copy(
        higher = lower + (higher - lower) / 2
    )

    fun upperHalf(): Range = copy(
        lower = lower + (higher - lower) / 2 + 1
    )
}