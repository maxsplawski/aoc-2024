fun main() {
    fun evaluate(initial: Long, expected: Long, operator: Char, operands: List<Long>): Boolean {
        if (initial > expected) return false
        if (initial == expected && operands.isEmpty()) return true
        if (operands.isEmpty()) return false
        val acc = when (operator) {
            '+' -> initial + operands[0]
            '*' -> initial * operands[0]
            '|' -> "$initial${operands[0]}".toLong()
            else -> 0
        }
        return evaluate(acc, expected, '+', operands.subList(1, operands.size)) || evaluate(acc, expected, '*', operands.subList(1, operands.size)) || evaluate(acc, expected, '|', operands.subList(1, operands.size))
    }

    fun part1(input: List<String>) = input
        .map { it.substringBefore(':').toLong() to it.substringAfter(':').split(" ").drop(1).map(String::toLong) }
        .sumOf { (result, operands) ->
            if (evaluate(0, result, '+', operands)) result else 0
        }


//    fun part2(input: List<String>): Int {
//
//    }

    val input = readInput("Day07")
    part1(input).println()
//    part2(input).println()
}
