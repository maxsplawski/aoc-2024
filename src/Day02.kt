import kotlin.math.abs

fun main() {
    fun isLineSafe(elements: List<Int>): Boolean {
        var isLineSafe = true
        for (i in 0 until elements.size - 1) {
            if ((elements[i] == elements[i + 1]) || (abs(elements[i] - elements[i + 1]) > 3)) {
                isLineSafe = false
                break
            }
        }
        if (!isLineSafe) return false
        if (elements.sorted() == elements || elements.sorted().reversed() == elements) {
            return true
        }
        return false
    }

    fun part1(input: List<String>): Int {
        return input.count { line ->
            isLineSafe(line.split(" ").map(String::toInt))
        }
    }

    fun part2(input: List<String>): Int {
        return input.count { line ->
            val elements = line.split(" ").map(String::toInt)
            isLineSafe(elements) || (0..elements.size).any { index ->
                isLineSafe(elements.take(index) + elements.drop(index + 1))
            }
        }
    }

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
