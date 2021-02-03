package se.vbgt.aoc.day8.part2

import se.vbgt.aoc.day8.common.Instruction
import se.vbgt.aoc.day8.common.Operation.*


fun day8part2(instructions: List<Instruction>) {
    println("Day8 part2: ${getAccumulator(instructions)}")
}

fun getAccumulator(instructions: List<Instruction>): Int {
    for (i in 0..instructions.size) {
        val instruction = instructions[i]
        when (instruction.operation) {
            ACC -> continue
            JMP -> {
                val acc = runInstructions(instructions.mapIndexed { i2, value -> if (i2 == i) value.copy(operation = NOP) else value })
                if (acc != null) return acc
            }
            NOP -> {
                val acc = runInstructions(instructions.mapIndexed { i2, value -> if (i2 == i) value.copy(operation = JMP) else value })
                if (acc != null) return acc
            }
        }
    }
    return -1
}

fun runInstructions(instructions: List<Instruction>): Int? {
    var currentInstruction = 0
    var accumulator = 0
    val visitedInstructions = HashSet<Int>()

    while (currentInstruction < instructions.size) {
        if (!visitedInstructions.add(currentInstruction))
            return null

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
    return accumulator
}