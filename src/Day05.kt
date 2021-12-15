fun main() {
    val testFileName = "Day05_test"
    val inputFileName = "Day05"

    val regex = """(\d*),(\d*) -> (\d*),(\d*)""".toRegex()

    data class Coordinate(val x: Int, val y: Int)

    fun MutableMap<Coordinate, Int>.markVerticalLine(coordinate1: Coordinate, coordinate2: Coordinate) {
        val start = minOf(coordinate1.y, coordinate2.y)
        val end = maxOf(coordinate1.y, coordinate2.y)
        for (i in start..end) {
            val newCoordinate = Coordinate(coordinate1.x, i)
            this[newCoordinate] = (this[newCoordinate] ?: 0) + 1
        }
    }

    fun MutableMap<Coordinate, Int>.markHorizontalLine(coordinate1: Coordinate, coordinate2: Coordinate) {
        val start = minOf(coordinate1.x, coordinate2.x)
        val end = maxOf(coordinate1.x, coordinate2.x)
        for (i in start..end) {
            val newCoordinate = Coordinate(i, coordinate1.y)
            this[newCoordinate] = (this[newCoordinate] ?: 0) + 1
        }
    }

    fun MutableMap<Coordinate, Int>.markDiagonalLine(coordinate1: Coordinate, coordinate2: Coordinate) {
        val xRange = if (coordinate2.x - coordinate1.x > 0) coordinate1.x..coordinate2.x else coordinate1.x downTo coordinate2.x
        val yRange = if (coordinate2.y - coordinate1.y > 0) coordinate1.y..coordinate2.y else coordinate1.y downTo coordinate2.y

        for ((i, j) in xRange.zip(yRange)) {
            val newCoordinate = Coordinate(i, j)
            this[newCoordinate] = (this[newCoordinate] ?: 0) + 1
        }
    }

    fun part1(input: List<String>): Int {
        val hydrothermalVentsMap = mutableMapOf<Coordinate, Int>()
        input.forEach { ventDefinition ->
            val (x1, y1, x2, y2) = regex.find(ventDefinition)?.destructured ?: error("Could not parse $ventDefinition")
            val coord1 = Coordinate(x1.toInt(), y1.toInt())
            val coord2 = Coordinate(x2.toInt(), y2.toInt())
            if ((coord1.x == coord2.x) xor (coord1.y == coord2.y)) {
                if (coord1.x == coord2.x) {
                    hydrothermalVentsMap.markVerticalLine(coord1, coord2)
                } else {
                    hydrothermalVentsMap.markHorizontalLine(coord1, coord2)
                }
            }
        }
        return hydrothermalVentsMap.values.filter { it > 1 }.size
    }

    fun part2(input: List<String>): Int {
        val hydrothermalVentsMap = mutableMapOf<Coordinate, Int>()
        input.forEach { ventDefinition ->
            val (x1, y1, x2, y2) = regex.find(ventDefinition)?.destructured ?: error("Could not parse $ventDefinition")
            val coord1 = Coordinate(x1.toInt(), y1.toInt())
            val coord2 = Coordinate(x2.toInt(), y2.toInt())
            if ((coord1.x == coord2.x) xor (coord1.y == coord2.y)) {
                if (coord1.x == coord2.x) {
                    hydrothermalVentsMap.markVerticalLine(coord1, coord2)
                } else {
                    hydrothermalVentsMap.markHorizontalLine(coord1, coord2)
                }
            } else {
                hydrothermalVentsMap.markDiagonalLine(coord1, coord2)
            }
        }
        return hydrothermalVentsMap.values.filter { it > 1 }.size
    }

    val testInput = readInput(testFileName)

    val expectedResultPart1 = 5
    val actualResultPart1 = part1(testInput)
    check(actualResultPart1 == expectedResultPart1) { "Part1: Result: $actualResultPart1 is not the expected one: $expectedResultPart1" }

    val expectedResultPart2 = 12
    val actualResultPart2 = part2(testInput)
    check(actualResultPart2 == expectedResultPart2) { "Part2: Result: $actualResultPart2 is not the expected one: $expectedResultPart2" }

    val input = readInput(inputFileName)
    println(part1(input))
    println(part2(input))
}