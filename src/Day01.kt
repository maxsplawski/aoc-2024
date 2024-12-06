import kotlin.math.abs

fun main() {
    fun sortedLists(input: List<String>): Pair<List<Int>, List<Int>> {
        val firstList = ArrayList<Int>()
        val secondList = ArrayList<Int>()

        input.forEach { line ->
            val (first, second) = line.split("   ")
            firstList.add(first.toInt())
            secondList.add(second.toInt())
        }

        return firstList.sorted() to secondList.sorted()
    }

    fun part1(input: List<String>): Int {
        val (first, second) = sortedLists(input)
        var totalDistance = 0

        for (index in first.indices) {
            val distance = abs(first[index] - second[index])
            totalDistance += distance
        }

        return totalDistance
    }

    fun part2(input: List<String>): Int {
        val (first, second) = sortedLists(input)
        var similarityScore = 0

        first.forEach { element ->
            val occurrences = second.count { element == it }
            similarityScore += element * occurrences
        }

        return similarityScore
    }

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
