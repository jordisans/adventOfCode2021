fun main() {
    val testFileName = "Day01_test"
    val inputFileName = "Day01"

    fun part1(input: List<Int>): Int {
        var measurementIncreases = 0
        input
            .reduce { l, r -> 
                if (r > l) measurementIncreases++
                r
             }

        return measurementIncreases
    }

    fun part2(input: List<Int>): Int {
        var i = 0
        var j = 1
        var windowMeasurementIncreases = 0
        while (j < input.size - 2) {
            val slidingWindowA = input[i] + input[i+1] + input[i+2]
            val slidingWindowB = input[j] + input[j+1] + input[j+2]
            if (slidingWindowB > slidingWindowA) windowMeasurementIncreases++
            i++
            j++
        }

        return windowMeasurementIncreases
    }

    val testInput = readInput(testFileName).map { it.toInt() }

    val expectedResultPart1 = 7
    val actualResultPart1 = part1(testInput)
    check(actualResultPart1 == expectedResultPart1) { "Part1: Result: $actualResultPart1 is not the expected one: $expectedResultPart1" }

    val expectedResultPart2 = 5
    val actualResultPart2 = part2(testInput)
    check(actualResultPart2 == expectedResultPart2) { "Part2: Result: $actualResultPart2 is not the expected one: $expectedResultPart2" }

    val input = readInput(inputFileName).map { it.toInt() }
    println(part1(input))
    println(part2(input))
}
