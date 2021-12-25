package day7

import java.io.File
import kotlin.math.abs

fun main (){
    val xPositions = File("src/main/kotlin/day7/input")
                    .readLines()[0]
                    .split(",")
                    .map { it.toInt() }
                    .toMutableList()
    xPositions.sort()
    val medianPosition = xPositions.median()
    println("Answer part 1: ${calcFuel(xPositions, medianPosition)}")

    println("Answer part 2: ${xPositions.findOptimalFuel()}")
}


fun MutableList<Int>.median(): Int {
    return if (this.size % 2 == 0) {
        ((this[this.size / 2] + this[this.size / 2 - 1]) / 2)
    } else {
        (this[this.size / 2])
    }
}

fun List<Int>.findOptimalFuel(): Int? {
    val min = this.minOrNull() ?: throw IllegalStateException("No min found")
    val max = this.maxOrNull() ?: throw IllegalStateException("No min found")

    var optimalFuel: Int? = null

    for (position in min..max) {
        val optimalFuelCandidate = calcFuelPart2(this, position)
        if (optimalFuel == null || optimalFuelCandidate < optimalFuel) {
            optimalFuel = optimalFuelCandidate
        }
    }

    return optimalFuel
}

// We assume that the median is in the list... which we manually checked
// otherwise we need to find the closest number to the median that is in the list
fun calcFuel(xPositions:List<Int>, median: Int): Int {
    var sumFuel = 0
    xPositions.forEach{
        sumFuel += abs(it-median) }

    return sumFuel
}

fun calcFuelPart2(xPositions: List<Int>, position: Int): Int {
    var sumFuel = 0
    xPositions.forEach {
        val diff = abs(it - position)
        for (i in 1 .. diff) {
            sumFuel += i
        }
    }
    return sumFuel
}
