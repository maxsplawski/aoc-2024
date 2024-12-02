import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val firstList = ArrayList<Int>()
        val secondList = ArrayList<Int>()

        input.forEach { line ->
            val (first, second) = line.split("   ")
            firstList.add(first.toInt())
            secondList.add(second.toInt())
        }

        val sortedFirstList = firstList.sorted()
        val sortedSecondList = secondList.sorted()
        var totalDistance = 0

        for ((index, _) in sortedFirstList.withIndex()) {
            val distance = abs(sortedFirstList[index] - sortedSecondList[index])
            totalDistance += distance
        }

        return totalDistance
    }

    fun part2(input: List<String>): Int {
        val firstList = ArrayList<Int>()
        val secondList = ArrayList<Int>()

        input.forEach { line ->
            val (first, second) = line.split("   ")
            firstList.add(first.toInt())
            secondList.add(second.toInt())
        }

        val sortedFirstList = firstList.sorted()
        val sortedSecondList = secondList.sorted()
        var similarityScore = 0

        sortedFirstList.forEach { element ->
            val occurrences = sortedSecondList.count { element == it }
            similarityScore += element * occurrences
        }

        return similarityScore
    }

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
