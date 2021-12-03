package day2

import java.io.File

fun main() {
    val instructions = File("src/main/kotlin/day2/input").readLines()

    val position = Position()
    val positionWithAim = PositionWithAim()

    instructions.forEach {
        val splitList = it.split(" ")
        val instruction = splitList[0]
        val steps = splitList[1].toInt()

        when (instruction) {
            "forward" -> {
                position.goForward(steps)
                positionWithAim.goForward(steps)
            }
            "down" -> {
                position.goDown(steps)
                positionWithAim.goDown(steps)
            }
            "up" -> {
                position.goUp(steps)
                positionWithAim.goUp(steps)
            }
            else -> {
                throw Exception("Unknown instruction: $instruction")
            }
        }
    }

    println("Answer part 1: ${position.y * position.x}")

    println("Answer part 2: ${positionWithAim.y * positionWithAim.x}")

}

