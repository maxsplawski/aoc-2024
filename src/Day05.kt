import kotlin.math.abs

fun main() {
    fun validate(update: List<Int>, rules: List<Pair<Int, Int>>): Boolean {
        for (i in 0..<update.lastIndex)
            for (j in i + 1..update.lastIndex)
                if (update[i] to update[j] !in rules) return false
        return true
    }

    fun part1(input: List<String>): Int {
        var result = 0
        val rules = input.filter { it.contains('|') }
        val updates = input
            .filter { !rules.contains(it) && it.isNotBlank() }
            .map { it.split(",").map(String::toInt) }
        val mappedRules = rules.map {
            val (left, right) = it.split('|').map(String::toInt)
            left to right
        }

        updates.forEach {
            if (validate(it, mappedRules)) {
                result += it[abs(it.size / 2)]
            }
        }

        return result
    }

    fun part2(input: List<String>): Int {
        val rules = input.filter { it.contains('|') }
        val updates = input
            .filter { !rules.contains(it) && it.isNotBlank() }
            .map { it.split(",").map(String::toInt).toMutableList() }
        val mappedRules = rules.map {
            val (left, right) = it.split('|').map(String::toInt)
            left to right
        }
        val validUpdates = updates
            .filterNot { validate(it, mappedRules) }

        return validUpdates.sumOf { update ->
            check@ while (true) {
                for (i in 0..<update.lastIndex) {
                    val left = update[i]
                    val j = i + 1
                    val right = update[j]
                    if (left to right !in mappedRules) {
                        update[i] = right
                        update[j] = left
                        continue@check
                    }
                }
                break
            }
            update[abs(update.size / 2)]
        }
    }

    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}
