package se.vbgt.aoc.year2020.day7.common

import java.io.File

private val selectBagNameAndContent = "(.+) bags? contain (.*)".toRegex()
private val selectBagNameAndCount = "(\\d+) (.*) bags?".toRegex()

fun readFileIntoBags(filename: String): Map<String, Map<String, Int>> =
    File(filename)
        .readLines()
        .mapNotNull { selectBagNameAndContent.find(it) }
        .map { it.groupValues[1] to stringToContents(it.groupValues[2]) }
        .toMap()

private fun stringToContents(text: String): Map<String, Int> =
    text.split("([.,])".toRegex())
        .map { it.trim() }
        .mapNotNull { selectBagNameAndCount.find(it) }
        .map { it.groupValues[2] to it.groupValues[1].toInt() }
        .toMap()
