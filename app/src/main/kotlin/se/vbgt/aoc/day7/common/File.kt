package se.vbgt.aoc.day7.common

import java.io.File

fun readFileIntoBags(filename: String): Map<String, Map<String, Int>> =
    File(filename)
        .readLines()
        .map { "(.+) bags? contain (.*)".toRegex().find(it) }
        .filterNotNull()
        .map { it.groupValues[1] to stringToContents(it.groupValues[2]) }
        .toMap()

private fun stringToContents(text: String): Map<String, Int> =
    text.split("([.,])".toRegex())
        .map { it.trim() }
        .map { "(\\d+) (.*) bags?".toRegex().find(it) }
        .filterNotNull()
        .map { it.groupValues[2] to it.groupValues[1].toInt() }
        .toMap()
