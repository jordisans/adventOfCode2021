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

    val expectedResultPart1 = 1
    val actualResultPart1 = part1(testInput)
    check(actualResultPart1 == expectedResultPart1) { "Part1: Result: $actualResultPart1 is not the expected one: $expectedResultPart1" }

//    val expectedResultPart2 = 1
//    val actualResultPart2 = part2(testInput)
//    check(actualResultPart2 == expectedResultPart2) { "Part2: Result: $actualResultPart2 is not the expected one: $expectedResultPart2" }

    val input = readInput(inputFileName)
    println(part1(input))
    println(part2(input))
}