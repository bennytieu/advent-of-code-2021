package day2

import java.io.File

fun main() {
    val instructions = File("src/main/kotlin/day2/input").readLines()

    val position = Position()

    instructions.forEach {
        val splitList = it.split(" ")
        val instruction = splitList[0]
        val steps = splitList[1].toInt()

        when (instruction) {
            "forward" -> position.goForward(steps)
            "down" -> position.goDown(steps)
            "up" -> position.goUp(steps)
            else -> {
                throw Exception("Unknown instruction: $instruction")
            }
        }
    }

    println("Answer part 1: ${position.y * position.x}")

}

