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

//    fun part2(input: List<String>): Int {
//
//    }

    val input = readInput("Day05")
    part1(input).println()
//    part2(input).println()
}
