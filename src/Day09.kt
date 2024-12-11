fun main() {
    fun part1(input: List<String>): Int {
        val fsMap = input[0]
        val fs = buildString {
            var fileIndex = 0
            fsMap.forEachIndexed { index, c ->
                val timesToAppend = c.digitToInt()
                if (index % 2 == 0) {
                    for (iteration in 0..<timesToAppend) {
                        append(fileIndex)
                    }
                    fileIndex++
                } else {
                    for (iteration in 0..<timesToAppend) {
                        append('.')
                    }
                }
            }
        }

        return 0
    }


//    fun part2(input: List<String>): Int {
//
//    }

    val input = readInput("Day09_test")
    part1(input).println()
//    part2(input).println()
}
