fun main() {
    val testFileName = "Day02_test"
    val inputFileName = "Day02"

    fun part1(input: List<String>): Int {
        val finalCoords = input.fold(object {
            var horizontalPosition = 0
            var depth = 0
        }) { coords, step ->
            val (direction, distance) = step.split(" ")
            when (direction) {
                "forward" -> coords.horizontalPosition += distance.toInt()
                "up" -> coords.depth -= distance.toInt()
                "down" -> coords.depth += distance.toInt()
            }
            coords
        }

        return finalCoords.depth * finalCoords.horizontalPosition
    }

    fun part2(input: List<String>): Int {
        val finalCoords = input.fold(object {
            var horizontalPosition = 0
            var depth = 0
            var aim = 0
        }) { coords, step ->
            val (direction, distanceString) = step.split(" ")
            val distance = distanceString.toInt()
            when (direction) {
                "forward" -> {
                    coords.horizontalPosition += distance
                    coords.depth += coords.aim * distance
                }
                "up" -> coords.aim -= distance
                "down" -> coords.aim += distance
            }
            coords
        }

        return finalCoords.depth * finalCoords.horizontalPosition
    }

    val testInput = readInput(testFileName)

    val expectedResultPart1 = 150
    val actualResultPart1 = part1(testInput)
    check(actualResultPart1 == expectedResultPart1) { "Part1: Result: $actualResultPart1 is not the expected one: $expectedResultPart1" }

    val expectedResultPart2 = 900
    val actualResultPart2 = part2(testInput)
    check(actualResultPart2 == expectedResultPart2) { "Part2: Result: $actualResultPart2 is not the expected one: $expectedResultPart2" }

    val input = readInput(inputFileName)
    println(part1(input))
    println(part2(input))
}