fun main() {
    val testFileName = "Day04_test"
    val inputFileName = "Day04"

    class Board(values: List<Int>) {
        val valuesTracker = values.associateWith { false }.toMutableMap()

        fun isNumberPresent(number: Int): Boolean {
            if (!valuesTracker.containsKey(number)) return false
            valuesTracker[number] = true
            return true
        }

        fun isCompleted(): Boolean {
            if (checkLines() || checkColumns()) return true
            return false
        }

        fun sumUnmarkedNumbers() = valuesTracker.filter { !it.value }.map { it.key }.sum()

        override fun toString(): String {
            var s = ""
            for (i in (0..4)) {
                s += "${valuesTracker.entries.drop(i*5).take(5)}\n"
            }
            return s
        }

        private fun checkLines(): Boolean {
            for (i in 0..4) {
                val line = valuesTracker.values.drop(i * 5).take(5).all { it }
                if (line) return true
            }
            return false
        }

        private fun checkColumns(): Boolean {
            for (i in 0..4) {
                val indexes = (i..24 step 5)
                val column = valuesTracker.values.filterIndexed { index, marked -> index in indexes }.all { it }
                if (column) return true
            }
            return false
        }
    }

    fun parseInput(input: List<String>): Pair<List<Int>, MutableList<Board>> {
        val randomNumbers = input.first().split(",").map { it.toInt() }

        val boards = mutableListOf<Board>()
        val boardValues = mutableListOf<Int>()

        for (inputLine in input.drop(1)) {
            if (inputLine.trim().isEmpty()) {
                if (boardValues.isNotEmpty()) {
                    boards.add(Board(boardValues))
                    boardValues.clear()
                }
                continue
            }
            boardValues.addAll(inputLine.split(" ").filter { it.trim().isNotEmpty() }.map { it.toInt() })
        }
        boards.add(Board(boardValues))
        return Pair(randomNumbers, boards)
    }

    fun part1(input: List<String>): Int {
        val (randomNumbers, boards) = parseInput(input)

        randomNumbers.forEach { number ->
            boards.forEach { board ->
                if (board.isNumberPresent(number) && board.isCompleted()) {
                    return board.sumUnmarkedNumbers() * number
                }
            }
        }

        return input.size
    }

    fun part2(input: List<String>): Int {
        val (randomNumbers, boards) = parseInput(input)
        randomNumbers.forEach { number ->
            val boardsToRemove = mutableListOf<Board>()
            boards.forEach { board ->
                if (board.isNumberPresent(number) && board.isCompleted()) {
                    if (boards.size == 1) {
                        return board.sumUnmarkedNumbers() * number
                    }
                    boardsToRemove.add(board)
                }
            }
            boards.removeAll(boardsToRemove)
        }

        return input.size
    }

    val testInput = readInput(testFileName)

    val expectedResultPart1 = 4512
    val actualResultPart1 = part1(testInput)
    check(actualResultPart1 == expectedResultPart1) { "Part1: Result: $actualResultPart1 is not the expected one: $expectedResultPart1" }

    val expectedResultPart2 = 1924
    val actualResultPart2 = part2(testInput)
    check(actualResultPart2 == expectedResultPart2) { "Part2: Result: $actualResultPart2 is not the expected one: $expectedResultPart2" }

    val input = readInput(inputFileName)
    println(part1(input))
    println(part2(input))
}