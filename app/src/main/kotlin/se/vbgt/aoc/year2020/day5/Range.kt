package se.vbgt.aoc.year2020.day5

data class Range(
    val lower: Int,
    val higher: Int
) {
    fun lowerHalf(): Range = this.copy(higher = this.lower + ((this.higher - this.lower) / 2))
    fun upperHalf(): Range = this.copy(lower = this.lower + ((this.higher - this.lower) / 2) + 1)
}