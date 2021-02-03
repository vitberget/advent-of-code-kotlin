package se.vbgt.aoc.day12.common

import java.io.File

fun readFileIntoActions(filename: String) =
    File(filename).readLines()
        .map {
            Action(
                actionFromLetter(it.first()),
                it.substring(1).toInt()
            )
        }
