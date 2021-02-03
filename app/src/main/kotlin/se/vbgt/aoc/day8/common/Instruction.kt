package se.vbgt.aoc.day8.common

data class Instruction(val operation:Operation, val number:Int)

enum class Operation {
    ACC,
    JMP,
    NOP;
}
