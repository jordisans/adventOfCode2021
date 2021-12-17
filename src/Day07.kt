import kotlin.math.abs

fun main() {
    val testFileName = "Day07_test"
    val inputFileName = "Day07"

    fun part1(input: List<Int>): Int {
        val sortedPositions = input.sorted()

        val median = sortedPositions[sortedPositions.size / 2]

        return sortedPositions.sumOf { crabPosition -> abs(crabPosition - median) }
    }

    // Bruteforcing here, there's a better solution for sure
    fun part2(input: List<Int>): Int {
        val sortedPositions = input.sorted()
        var minimumFuel = Int.MAX_VALUE
        for (possiblePosition in sortedPositions.first()..sortedPositions.last()) {
            val fuel = sortedPositions.sumOf { crabPosition -> (1..abs(crabPosition - possiblePosition)).sum() }
            if ( fuel < minimumFuel) {
                minimumFuel = fuel
            }
        }
        return minimumFuel
    }

    val testInput = readSingleLineOfIntegers(testFileName)

    val expectedResultPart1 = 37
    val actualResultPart1 = part1(testInput)
    check(actualResultPart1 == expectedResultPart1) { "Part1: Result: $actualResultPart1 is not the expected one: $expectedResultPart1" }

    val expectedResultPart2 = 168
    val actualResultPart2 = part2(testInput)
    check(actualResultPart2 == expectedResultPart2) { "Part2: Result: $actualResultPart2 is not the expected one: $expectedResultPart2" }

    val input = readSingleLineOfIntegers(inputFileName)
    println(part1(input))
    println(part2(input))
}