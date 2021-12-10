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
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput(inputFileName).map { it.toInt() }
    println(part1(input))
    println(part2(input))
}
