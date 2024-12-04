
fun main() {
    fun part1(input: List<String>): Int {
        var result = 0

        input.forEach { line ->
            result += Regex("XMAS").findAll(line).count()
        }

        input.forEach { line ->
            result += Regex("XMAS").findAll(line.reversed()).count()
        }

        val pivotedInput = ArrayList<String>()
        val size = input.first().length
        (0..<size).forEach { i ->
            val pivotedLine = ArrayList<Char>()
            (0..<size).forEach { j ->
                pivotedLine.add(input[j][i])
            }
            pivotedInput.add(pivotedLine.joinToString(""))
        }

        pivotedInput.forEach { line ->
            result += Regex("XMAS").findAll(line).count()
        }

        pivotedInput.forEach { line ->
            result += Regex("XMAS").findAll(line.reversed()).count()
        }

        val width = input.first().length
        val height = input.size

        val tippedRightInput = ArrayList<String>()
        var counter1 = 0
        while (counter1 < 2 * width - 1) {
            val tippedList = ArrayList<Char>()
            for (i in (0..<height)) {
                for (j in (0..<width)) {
                    if (i + j == counter1) tippedList.add(input[i][j])
                }
            }
            tippedRightInput.add(tippedList.joinToString(""))
            counter1++
        }

        val reversedInput = input.map { line -> line.reversed() }
        val tippedLeftInput = ArrayList<String>()
        var counter2 = 0
        while (counter2 < 2 * width - 1) {
            val tippedList = ArrayList<Char>()
            for (i in (0 ..<height)) {
                for (j in (0 ..<width)) {
                    if (i + j == counter2) tippedList.add(reversedInput[i][j])
                }
            }
            tippedLeftInput.add(tippedList.joinToString(""))
            counter2++
        }

        tippedRightInput.forEach { line ->
            result += Regex("XMAS").findAll(line).count()
        }

        tippedRightInput.forEach { line ->
            result += Regex("XMAS").findAll(line.reversed()).count()
        }

        tippedLeftInput.forEach { line ->
            result += Regex("XMAS").findAll(line).count()
        }

        tippedLeftInput.forEach { line ->
            result += Regex("XMAS").findAll(line.reversed()).count()
        }

      return result
    }

    fun part2(input: List<String>): Int {
        val width = input.first().length
        val height = input.size
        var result = 0

        for (i in (0..<height)) {
            if (i == 0 || i == (height - 1)) continue
            for (j in (0..<width)) {
                if (j == 0 || j == (width - 1)) continue
                if (input[i][j] == 'A') {
                    if (input[i - 1][j - 1] == 'M') {
                        if (input[i - 1][j + 1] == 'M') {
                            if ((input[i + 1][j - 1] == 'S') && input[i + 1][j + 1] == 'S') {
                                result++
                                continue
                            }
                        }
                        if (input[i - 1][j + 1] == 'S') {
                            if ((input[i + 1][j - 1] == 'M') && input[i + 1][j + 1] == 'S') {
                                result++
                                continue
                            }
                        }
                    }
                    if (input[i - 1][j - 1] == 'S') {
                        if (input[i - 1][j + 1] == 'S') {
                            if ((input[i + 1][j - 1] == 'M') && input[i + 1][j + 1] == 'M') {
                                result++
                                continue
                            }
                        }
                        if (input[i - 1][j + 1] == 'M') {
                            if ((input[i + 1][j - 1] == 'S') && input[i + 1][j + 1] == 'M') {
                                result++
                                continue
                            }
                        }
                    }
                }
            }
        }

        return result
    }

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
