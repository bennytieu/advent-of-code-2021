package day4

import java.io.File

fun main() {
    val bingoInput = File("src/main/kotlin/day4/input").readLines()
    val markers = getMarkers(bingoInput)
    val boards = getBoards(bingoInput)

    // Part 1
    val (number, board) = findWinningBoard(markers, boards)
    if (number!=null && board!=null) {
        val sum = board.sumUnmarkedNumbers()
        println("Answer part 1: ${sum * number}")
    }


    // Part 2
    val candidateBoards = boards.toMutableList()
    candidateBoards.removeWinningBoard(markers)

    val (lastNumber, winningBoard) = findWinningBoard(markers, listOf(candidateBoards[0]))
    if (lastNumber!=null && winningBoard!=null) {
        val sum = winningBoard.sumUnmarkedNumbers()
        println("Answer part 2: ${sum * lastNumber}")
    }
}

fun getMarkers(bingoInput: List<String>): List<Int> {
    return bingoInput[0].split(",").map { it.toInt() }
}

fun getBoards(bingoInput: List<String>): List<BingoBoard> {
    val boards = mutableListOf<BingoBoard>()

    for (i in 2 until bingoInput.size step 6) {
        val board = BingoBoard()

        val boardData = bingoInput.subList(i, i + 5)
        boardData.forEachIndexed { row, line ->
            val rowData = line.split(" ").filter { it.isNotEmpty() }.map { it.toInt() }

            rowData.forEachIndexed { column, value ->
                board.matrix[row][column] = BingoNumber(value)
            }
        }
        boards.add(board)
    }

    return boards
}

fun findWinningBoard(numbers: List<Int>, boards: List<BingoBoard>): Pair<Int?, BingoBoard?> {
    numbers.forEach { number ->
        boards.forEach { board ->
            board.mark(number)
            if (board.isWinner()) return Pair(number, board)
        }
    }

    // No winning board
    return Pair(null, null)
}

fun MutableList<BingoBoard>.removeWinningBoard(markers: List<Int>):List<BingoBoard> {
    while (this.size > 1) {
        val (_, winningBoard) = findWinningBoard(markers, this)
        this.remove(winningBoard)
    }

    return this
}
