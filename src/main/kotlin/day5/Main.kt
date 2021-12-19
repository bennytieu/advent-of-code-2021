package day5

import java.io.File

fun main() {
    val lines = File("src/main/kotlin/day5/input").readLines()

    val pointsPart1 = getLines(lines)
        .filter { it.isVertical || it.isHorizontal }
        .map{ it.getPoints()}
        .flatten()

    val numDuplicatePoints = getDuplicates(pointsPart1).size
    // 5608
    println("Answer part 1: $numDuplicatePoints")

    val pointsPart2 = getLines(lines)
                        .map{ it.getPoints()}
                        .flatten()

    val numDuplicatePointsInclDiagonals = getDuplicates(pointsPart2).size
    // 20299
    println("Answer part 2: $numDuplicatePointsInclDiagonals")
}

fun getLines(lines: List<String>): List<Line> {
    return lines.map { line ->
        val (point1, point2) =  line.split(" -> ")
        val (x1, y1) = point1.split(",")
        val (x2, y2) = point2.split(",")
        Line(x1.toInt(), y1.toInt(), x2.toInt(), y2.toInt())
    }
}

fun getDuplicates(points: List<Pair<Int, Int>>): List<Pair<Int, Int>>{
    // Key=Point, Value=Count
    val countMap = mutableMapOf<Pair<Int, Int>, Int>()

    for (point in points) {
        val count = countMap.getOrDefault(point, 0)
        countMap[point] = count + 1
    }

    return countMap.filter{ it.value > 1}.keys.toList()
}
