fun main() {
    val testFileName = "Day04_test"
    val inputFileName = "Day04"

    fun MutableMap<Int, Boolean>.isNumberPresent(number: Int): Boolean{
        if (!this.containsKey(number)) return false
        this[number] = true
        return true
    }

    fun MutableMap<Int, Boolean>.checkLines(): Boolean {
        for (i in 0..4) {
            val line = this.values.drop(i * 5).take(5).all { it }
            if (line) return true
        }
        return false
    }

    fun MutableMap<Int, Boolean>.checkColumns(): Boolean {
        for (i in 0..4) {
            val indexes = (i..24 step 5)
            val column = this.values.filterIndexed { index, marked -> index in indexes }.all { it }
            if (column) return true
        }
        return false
    }

    fun MutableMap<Int, Boolean>.isCompleted(): Boolean {
        if (this.checkLines() || this.checkColumns()) return true
        return false
    }

    fun MutableMap<Int, Boolean>.sumUnmarkedNumbers() = this.filter { !it.value }.map { it.key }.sum()

    fun parseInput(input: List<String>): Pair<List<Int>, MutableList<MutableMap<Int, Boolean>>> {
        val randomNumbers = input.first().split(",").map { it.toInt() }

        val boards = mutableListOf<MutableMap<Int, Boolean>>()

        for (inputLine in input.drop(1)) {
            if (inputLine.trim().isEmpty()) {
                boards.add(mutableMapOf())
                continue
            }
            boards.last().putAll(inputLine.split(" ").filter { it.trim().isNotEmpty() }.associate { it.toInt() to false })
        }
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
            val boardsToRemove = mutableListOf<MutableMap<Int, Boolean>>()
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