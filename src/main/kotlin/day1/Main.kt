package day1

import java.io.File

fun main() {
    val measurements = File("src/main/kotlin/day1/input").readLines().map{ it.toInt() }

    println("Answer part 1: ${countDepthIncrease(measurements)}")

    println("Answer part 2: ${countDepthIncreaseSlidingWindow(measurements)}")
}

// Part 1: How many measurements are larger than the previous measurement?
fun countDepthIncrease(measurements: List<Int>) : Int {
    var count = 0
    measurements.reduce { previous, current ->  if (current > previous) count++; current }
    return count
}

// Part 2: How many sums are larger than the previous sum?
fun countDepthIncreaseSlidingWindow(measurements: List<Int>) : Int {
    val listOfThree = mutableListOf<Int>()

    var sumWindow = 0
    for (i in measurements.indices) {
        for (y in 0..2) {
            if (i+y >= measurements.size) break
            sumWindow += measurements[i+y]
        }
        listOfThree.add(sumWindow)
        sumWindow = 0
    }

    return countDepthIncrease(listOfThree)
}

