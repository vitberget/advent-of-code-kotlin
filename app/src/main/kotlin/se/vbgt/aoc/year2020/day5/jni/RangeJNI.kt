package se.vbgt.aoc.year2020.day5.jni

import se.vbgt.jni.NativeRangeLib
import se.vbgt.jni.RangeJava

data class RangeJNI(
    val lower: Int,
    val upper: Int,
) {
    fun lowerHalf(): RangeJNI =
        copy(
            upper = NativeRangeLib.lowerHalf(
                toIntArray()
            )[1]
        )

    fun upperHalf(): RangeJNI =
        copy(
            lower = NativeRangeLib.upperHalf(
                RangeJava(lower, upper)
            ).lower
        )

    private fun toIntArray(): IntArray =
        IntArray(2).apply {
            this[0] = lower
            this[1] = upper
        }
}