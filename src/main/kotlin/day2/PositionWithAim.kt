package day2

class PositionWithAim {
    var y: Int = 0
    var x: Int = 0
    var aim: Int = 0

    fun goForward(steps: Int) {
        x += steps
        y += aim * steps
    }

    fun goUp(steps: Int) {
        aim -= steps
    }

    fun goDown(steps: Int) {
        aim += steps
    }
}