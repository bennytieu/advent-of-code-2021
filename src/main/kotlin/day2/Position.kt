package day2

class Position {
    var y: Int = 0
    var x: Int = 0

    fun goForward(steps: Int) {
        x += steps
    }

    fun goUp(steps: Int) {
        y -= steps
    }

    fun goDown(steps: Int) {
        y += steps
    }
}