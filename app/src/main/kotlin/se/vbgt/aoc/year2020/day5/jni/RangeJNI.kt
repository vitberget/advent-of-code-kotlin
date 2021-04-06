package se.vbgt.aoc.year2020.day5.jni

import se.vbgt.jni.NativeRangeLib
import se.vbgt.jni.RangeJava

data class RangeJNI(
    val lower: Int,
    val higher: Int,
) {
    fun lowerHalf(): RangeJNI =
        copy(
            higher = NativeRangeLib.lowerHalf(toIntArray())[1]
        )

    fun upperHalf(): RangeJNI =
        copy(
            lower = NativeRangeLib.upperHalf(RangeJava(lower,higher)).lower
        )

    private fun toIntArray(): IntArray =
        IntArray(2).apply {
            this[0] = lower
            this[1] = higher
        }
}