package day4

data class BingoBoard(
    val matrix: Array<Array<BingoNumber>> = Array(5) { Array(5) { BingoNumber(0) } }
) {
    override fun toString(): String {
        return matrix.joinToString("\n") { row ->
            row.joinToString(" ") { bingoNumber ->
                if (bingoNumber.isMarked) "X" else bingoNumber.value.toString()
            }
        }
    }

    fun mark(value: Int) {
        matrix.forEach { row ->
            row.forEach { bingoNumber ->
                if (bingoNumber.value == value) bingoNumber.isMarked = true
            }
        }
    }

    fun sumUnmarkedNumbers(): Int {
        return matrix.flatten().filter { !it.isMarked }.sumOf { it.value }
    }

    fun isWinner(): Boolean {
        return isWinRow() || isWinColumn()
    }

    private fun isWinRow(): Boolean {
        for (y in 0 until 5) {
            var winner = true
            for (x in 0 until 5) {
                if (!matrix[y][x].isMarked) winner = false
            }
            if (winner) return true
        }

        return false
    }

    private fun isWinColumn(): Boolean {
        for (x in 0 until 5) {
            var winner = true
            for (y in 0 until 5) {
                if (!matrix[y][x].isMarked) winner = false
            }
            if (winner) return true
        }

        return false
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BingoBoard

        if (!matrix.contentDeepEquals(other.matrix)) return false

        return true
    }

    override fun hashCode(): Int {
        return matrix.contentDeepHashCode()
    }
}