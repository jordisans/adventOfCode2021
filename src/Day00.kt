fun main() {
    val testFileName = "Day00_test"
    val inputFileName = "Day00"
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val testInput = readInput(testFileName)
    check(part1(testInput) == 1)

    val input = readInput(inputFileName)
    println(part1(input))
    println(part2(input))
}