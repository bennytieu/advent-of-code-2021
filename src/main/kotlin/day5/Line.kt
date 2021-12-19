package day5

data class Line (
    val x1: Int,
    val y1: Int,
    val x2: Int,
    val y2: Int,
) {
    val isHorizontal: Boolean = y1 == y2
    val isVertical: Boolean = x1 == x2
    val isDiagonal: Boolean = !isHorizontal && !isVertical

    fun getPoints(): List<Pair<Int, Int>> {
        val points = mutableListOf<Pair<Int,Int>>()
        if (isHorizontal) {
            val startX = x1.coerceAtMost(x2)
            val endX =  x1.coerceAtLeast(x2)

            for(x in startX .. endX) {
                points.add(Pair(x, y1))
            }
        } else if (isVertical) {
            val startY = y1.coerceAtMost(y2)
            val endY = y1.coerceAtLeast(y2)

            for (y in startY .. endY) {
                points.add(Pair(x1, y))
            }
        } else if (isDiagonal) {
            val startDiagonalPoint = Pair(x1, y1)
            val endDiagonalPoint = Pair(x2, y2)
            val diffX = endDiagonalPoint.first - startDiagonalPoint.first
            val diffY = endDiagonalPoint.second - startDiagonalPoint.second
            val stepX = if (diffX > 0) 1 else -1
            val stepY = if (diffY > 0) 1 else -1
            var x = startDiagonalPoint.first
            var y = startDiagonalPoint.second
            while (x != endDiagonalPoint.first || y != endDiagonalPoint.second) {
                points.add(Pair(x, y))
                x += stepX
                y += stepY
            }
            points.add(Pair(x, y))
            return points

        } else {
            // Should not happen...?
        }
        return points
    }
}