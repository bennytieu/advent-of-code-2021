package day3

import java.io.File

fun main() {
    val rows = File("src/main/kotlin/day3/input").readLines()

    val powerConsumption = calcPowerConsumption(rows)
    val lifeSupportRating = calcLifeSupportRating(rows)

    println("Answer part 1: ${powerConsumption.gamma * powerConsumption.epsilon}")
    println("Answer part 2: ${lifeSupportRating.oxygenGeneratorRating * lifeSupportRating.cO2ScrubberRating}")

}

fun calcPowerConsumption(rows: List<String>): PowerConsumption {
    val numCols = rows[0].length

    var gamma = ""
    var epsilon = ""

    for (col in 0 until numCols) {
        val onesAndZeroesPair = calcOnesAndZeroes(rows, col)
        val numOnes = onesAndZeroesPair.first
        val numZeroes = onesAndZeroesPair.second

        gamma += if (numOnes > numZeroes) "1" else "0"
        epsilon += if (numOnes < numZeroes) "1" else "0"
    }

    return PowerConsumption(gamma = gamma.toInt(2), epsilon = epsilon.toInt(2))
}

fun calcLifeSupportRating(rows: List<String>): LifeSupportRating {
    val numCols = rows[0].length

    var mutableRows = rows.toMutableList()

    var oxygenGeneratorRating = ""

    for (col in 0 until numCols) {
        val onesAndZeroesPair = calcOnesAndZeroes(mutableRows, col)
        val numOnes = onesAndZeroesPair.first
        val numZeroes = onesAndZeroesPair.second

        if (numOnes >= numZeroes) {
            mutableRows = mutableRows.filter { row -> row[col] == '1' } as MutableList<String>
        } else if (numOnes < numZeroes) {
            mutableRows = mutableRows.filter { row -> row[col] == '0' } as MutableList<String>
        }

        if (mutableRows.size == 1) {
            oxygenGeneratorRating = mutableRows[0]
            break
        }
    }

    mutableRows = rows.toMutableList()
    var cO2ScrubberRating = ""

    for (col in 0 until numCols) {
        val onesAndZeroesPair = calcOnesAndZeroes(mutableRows, col)
        val numOnes = onesAndZeroesPair.first
        val numZeroes = onesAndZeroesPair.second

        if (numOnes >= numZeroes) {
            mutableRows = mutableRows.filter { row -> row[col] == '0' } as MutableList<String>
        } else if (numOnes < numZeroes) {
            mutableRows = mutableRows.filter { row -> row[col] == '1' } as MutableList<String>
        }

        if (mutableRows.size == 1) {
            cO2ScrubberRating = mutableRows[0]
            break
        }
    }

    return LifeSupportRating(
        oxygenGeneratorRating = oxygenGeneratorRating.toInt(2),
        cO2ScrubberRating = cO2ScrubberRating.toInt(2)
    )
}

fun calcOnesAndZeroes(rows: List<String>, col: Int): Pair<Int, Int> {
    var numOnes = 0
    var numZeroes = 0

    rows.forEach { row ->
        when (row[col]) {
            '0' -> numZeroes++
            '1' -> numOnes++
            else -> throw Exception("Unknown input: ${row[col]}")
        }
    }

    return Pair(numOnes, numZeroes)
}
