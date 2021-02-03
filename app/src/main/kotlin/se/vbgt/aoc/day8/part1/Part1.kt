package se.vbgt.aoc.day8.part1

import se.vbgt.aoc.day8.common.Instruction
import se.vbgt.aoc.day8.common.Operation.*

fun day8part1(instructions: List<Instruction>) {
    var currentInstruction = 0
    var accumulator = 0
    val visitedInstructions = HashSet<Int>()

    while (!visitedInstructions.contains(currentInstruction)) {
        visitedInstructions.add(currentInstruction)
        val instruction = instructions[currentInstruction]

        when (instruction.operation) {
            NOP -> currentInstruction++
            JMP -> currentInstruction += instruction.number
            ACC -> {
                currentInstruction++
                accumulator += instruction.number
            }
        }
    }

    println("Day8 part1: ${accumulator}")
}