fun main() {
    val testFileName = "Day06_test"
    val inputFileName = "Day06"

    fun part1(input: String): Int {
        val lanternFishPopulation = input.split(",").map { it.toInt() }.toMutableList()
        var lanternFishBorn = 0
        for (i in 1..80) {
            lanternFishPopulation.forEachIndexed { index, internalTimer ->
                if (internalTimer == 0) {
                    lanternFishPopulation[index] = 6
                    lanternFishBorn++
                } else {
                    lanternFishPopulation[index] = internalTimer - 1
                }
            }
            lanternFishBorn
                .takeIf { it > 0 }
                ?.let {
                    lanternFishPopulation.addAll((0 until lanternFishBorn).map { 8 })
                }
            lanternFishBorn = 0
        }
        return lanternFishPopulation.size
    }

    fun part2(input: String): Long {
        var lanternFishPopulation = input.split(",").map { it.toInt() }
            .groupBy { it }
            .mapValues { (_, occurrences) -> occurrences.size.toLong() }

        val nextDayPopulation = mutableMapOf<Int, Long>()

        for (i in 1..256) {
            lanternFishPopulation.forEach { (internalTimer, numberOfFish) ->
                if (internalTimer > 0) {
                    nextDayPopulation[internalTimer - 1] = (nextDayPopulation[internalTimer-1] ?: 0) + numberOfFish
                } else {
                    nextDayPopulation[6] = (nextDayPopulation[6] ?: 0) + numberOfFish
                    nextDayPopulation[8] = numberOfFish
                }
            }
            lanternFishPopulation = nextDayPopulation.toMap()
            nextDayPopulation.clear()
        }

        return lanternFishPopulation.values.sum()
    }

    val testInput = readInput(testFileName).first()

    val expectedResultPart1 = 5934
    val actualResultPart1 = part1(testInput)
    check(actualResultPart1 == expectedResultPart1) { "Part1: Result: $actualResultPart1 is not the expected one: $expectedResultPart1" }

    val expectedResultPart2 = 26984457539
    val actualResultPart2 = part2(testInput)
    check(actualResultPart2 == expectedResultPart2) { "Part2: Result: $actualResultPart2 is not the expected one: $expectedResultPart2" }

    val input = readInput(inputFileName).first()
    println(part1(input))
    println(part2(input))
}