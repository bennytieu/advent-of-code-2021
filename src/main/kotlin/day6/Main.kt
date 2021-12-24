import day6.LanternFish
import java.io.File

fun main (){
    val internalTimers = File("src/main/kotlin/day6/input").readLines()[0].split(",").map { it.toInt() }

    val lanternFishes = internalTimers.map { LanternFish(it) }

    println("Answer part 1: ${simulate80Days(lanternFishes).size}")

}

fun simulate80Days(lanternFishes: List<LanternFish>): List<LanternFish> {
    val mutLanternFishes = lanternFishes.toMutableList()
    for (i in 1 .. 80) {
        val additionalFishes = mutableListOf<LanternFish>()
        for (fish in mutLanternFishes) {
            if (fish.internalTimer == 0) {
                additionalFishes.add(LanternFish())
                fish.resetInternalTimer()
            } else {
                fish.decreaseInternalDay()
            }
        }
        mutLanternFishes.addAll(additionalFishes)

//      print("Day $i: ")
//      println(mutLanternFishes.forEach{ print(""+it.internalTimer + ",")})
    }

    return mutLanternFishes
}
