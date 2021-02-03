package se.vbgt.aoc.day8.part1

import se.vbgt.aoc.day8.common.Instruction
import se.vbgt.aoc.day8.common.Operation.*

fun day8part1(instructions: List<Instruction>) {
    var currentInstruction = 0
    var accumulator = 0
    val visitedInstructions = HashSet<Int>()

    while (visitedInstructions.add(currentInstruction)) {
        val (operation, number) = instructions[currentInstruction]
        when (operation) {
            NOP -> currentInstruction++
            JMP -> currentInstruction += number
            ACC -> {
                currentInstruction++
                accumulator += number
            }
        }
    }

    println("Day8 part1: ${accumulator}")
}