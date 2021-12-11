import java.lang.IllegalArgumentException

fun main() {
    val testFileName = "Day03_test"
    val inputFileName = "Day03"

    fun part1(input: List<String>): Int {
        val inputHalfSize = input.size / 2

        // Counting digits.
        val oneBits = (0 until input.first().length).associateWith { 0 }.toMutableMap()
        input.forEach { binaryNumber ->
            binaryNumber.forEachIndexed { i, bit ->
                bit.takeIf { it == '1' }?.let { oneBits[i] = oneBits[i]!!.plus(1) }
            }
        }

        // Building resulting rates
        val (gammaRate, epsilonRate) = oneBits.map { (_, numberOfOnes) ->
            if (numberOfOnes > inputHalfSize) Pair('1', '0') else Pair('0', '1')
        }.fold(Pair("", "")) { acc, nextDigits ->
            acc.copy(
                first = acc.first.plus(nextDigits.first),
                second = acc.second.plus(nextDigits.second)
            )
        }

        println("gamma $gammaRate, epsilon $epsilonRate")

        return gammaRate.toInt(2) * epsilonRate.toInt(2)
    }

    fun findRating(binaryNumbers: List<String>, bitIndex: Int, criteria: (List<String>, List<String>) -> List<String>): String {
        if (binaryNumbers.isEmpty()) throw IllegalArgumentException("No binary numbers left to search through")
        if (binaryNumbers.size == 1) return binaryNumbers.first()
        if (bitIndex >= binaryNumbers.first().length) throw IllegalArgumentException("No more bits to filter by")

        val (startsWithOne, startsWithZero) = binaryNumbers.partition { binaryNumber -> binaryNumber[bitIndex] == '1' }
        val remainingNumbers = criteria(startsWithOne, startsWithZero)

        return findRating(remainingNumbers, bitIndex + 1, criteria)
    }

    fun part2(input: List<String>): Int {
        val oxygenGeneratorRating = findRating(input, 0) { numbersStartingWithOne, numbersStartingWithZero ->
            if (numbersStartingWithOne.size >= numbersStartingWithZero.size) numbersStartingWithOne else numbersStartingWithZero
        }
        val CO2ScrubberRating = findRating(input, 0) { numbersStartingWithOne, numbersStartingWithZero ->
            if (numbersStartingWithZero.size <= numbersStartingWithOne.size) numbersStartingWithZero else numbersStartingWithOne
        }

        println("oxygen $oxygenGeneratorRating, CO2 $CO2ScrubberRating")
        return oxygenGeneratorRating.toInt(2) * CO2ScrubberRating.toInt(2)
    }

    val testInput = readInput(testFileName)

    val expectedResultPart1 = 198
    val actualResultPart1 = part1(testInput)
    check(actualResultPart1 == expectedResultPart1) { "Part1: Result: $actualResultPart1 is not the expected one: $expectedResultPart1" }

    val expectedResultPart2 = 230
    val actualResultPart2 = part2(testInput)
    check(actualResultPart2 == expectedResultPart2) { "Part2: Result: $actualResultPart2 is not the expected one: $expectedResultPart2" }

    val input = readInput(inputFileName)
    println(part1(input))
    println(part2(input))
}