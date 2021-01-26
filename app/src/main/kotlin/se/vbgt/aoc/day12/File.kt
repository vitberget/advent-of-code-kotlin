package se.vbgt.aoc.day12

import java.io.File

fun readFileIntoInstructions(filename: String) =
    File(filename).readLines()
        .map {
            Action(
                actionFromLetter(it.first()),
                it.substring(1).toInt()
            )
        }
