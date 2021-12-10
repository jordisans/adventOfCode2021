fun main() {
    val testFileName = "Day02_test"
    val inputFileName = "Day02"
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