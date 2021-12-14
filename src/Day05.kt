fun main() {
    val testFileName = "Day05_test"
    val inputFileName = "Day05"

    val regex = """(\d*),(\d*) -> (\d*),(\d*)""".toRegex()

    data class Coordinate(val x: Int, val y: Int)

    fun part1(input: List<String>): Int {
        val hydrothermalVentsMap = mutableMapOf<Coordinate, Int>()
        input.forEach { ventDefinition ->
            val (x1, y1, x2, y2) = regex.find(ventDefinition)?.destructured ?: error("Could not parse $ventDefinition")
            val coord1 = Coordinate(x1.toInt(), y1.toInt())
            val coord2 = Coordinate(x2.toInt(), y2.toInt())
            if ((coord1.x == coord2.x) xor (coord1.y == coord2.y)) {
                if (coord1.x == coord2.x) {
                    val start = minOf(coord1.y, coord2.y)
                    val end = maxOf(coord1.y, coord2.y)
                    for (i in start..end) {
                        val coord = Coordinate(coord1.x, i)
                        hydrothermalVentsMap[coord] = (hydrothermalVentsMap[coord] ?: 0) + 1
                    }
                } else {
                    val start = minOf(coord1.x, coord2.x)
                    val end = maxOf(coord1.x, coord2.x)
                    for (i in start..end) {
                        val coord = Coordinate(i, coord1.y)
                        hydrothermalVentsMap[coord] = (hydrothermalVentsMap[coord] ?: 0) + 1
                    }
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
                    val start = minOf(coord1.y, coord2.y)
                    val end = maxOf(coord1.y, coord2.y)
                    for (i in start..end) {
                        val coord = Coordinate(coord1.x, i)
                        hydrothermalVentsMap[coord] = (hydrothermalVentsMap[coord] ?: 0) + 1
                    }
                } else {
                    val start = minOf(coord1.x, coord2.x)
                    val end = maxOf(coord1.x, coord2.x)
                    for (i in start..end) {
                        val coord = Coordinate(i, coord1.y)
                        hydrothermalVentsMap[coord] = (hydrothermalVentsMap[coord] ?: 0) + 1
                    }
                }
            } else {
                // Diagonal
                val xRange = if (coord2.x - coord1.x > 0) coord1.x..coord2.x else coord1.x downTo  coord2.x
                val yRange = if (coord2.y - coord1.y > 0) coord1.y..coord2.y else coord1.y downTo  coord2.y

                for ((i,j) in xRange.toList().zip(yRange.toList())) {
                    val coord = Coordinate(i, j)
                    hydrothermalVentsMap[coord] = (hydrothermalVentsMap[coord] ?: 0) + 1
                }
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