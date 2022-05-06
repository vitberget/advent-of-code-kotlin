package se.vbgt.aoc.year2020.day8.common

import java.io.File

fun fileToInstructions(filename:String) : List<Instruction> =
    File(filename).readLines()
        .map { it.split(" ")}
        .map { Instruction(Operation.valueOf(it[0].uppercase()), it[1].toInt()) }
