package day6

data class LanternFish(
    var internalTimer: Int = 8
) {
    fun decreaseInternalDay() {
        internalTimer--
    }

    fun resetInternalTimer(){
        internalTimer = 6
    }

}
