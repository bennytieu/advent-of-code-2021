import java.io.File

fun main (){
    val internalTimers = File("src/main/kotlin/day6/input").readLines()[0].split(",").map { it.toInt() }

    val lanternFishesMapPart1 = createLanternFishesMap(internalTimers)
    val lanternFishesMapPart2 = createLanternFishesMap(internalTimers)

    for (i in 1 .. 80) {
        lanternFishesMapPart1.simulate()
    }

    for (i in 1 .. 256) {
        lanternFishesMapPart2.simulate()
    }

    // 362346
    println("Answer part 1: ${lanternFishesMapPart1.getFishCount()}")

    // 1639643057051
    println("Answer part 2: ${lanternFishesMapPart2.getFishCount()}")

}

// Key = Internal Timer k in Key (where k is [0,1,..,8])
// Value = The number of LanternFishes
fun createLanternFishesMap(internalTimers: List<Int>): MutableMap<Int, Long> {
    val lanternFishesMap = mutableMapOf<Int, Long>()

    internalTimers.forEach { fish ->
        val count = lanternFishesMap.getOrDefault(fish, 0)
        lanternFishesMap[fish] = count + 1
    }

    return lanternFishesMap
}

fun MutableMap<Int, Long>.getFishCount(): Long {
    return values.sum()
}

fun MutableMap<Int, Long>.simulate() {
    val additionalFishes = get(0) ?: 0

    for (i in 1 .. 8) {
        val internalTime = i-1
        val internalTimeCount = get(i) ?: 0
        put(internalTime, internalTimeCount)
    }

    put(8, additionalFishes)
    val numOfFishesWithInternalTime6 = get(6) ?: 0
    put(6, numOfFishesWithInternalTime6 + additionalFishes)
}
