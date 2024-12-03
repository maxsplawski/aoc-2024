
fun main() {
    fun part1(input: List<String>): Int {
        val occurrences = ArrayList<String>()
        val calculationInput = ArrayList<Int>()
        var calculation = 0

        val outerRegex = Regex("mul\\([0-9]{1,3},[0-9]{1,3}\\)")
        input.forEach { line ->
            outerRegex.findAll(line).forEach { result ->
                occurrences.add(result.groupValues[0])
            }
        }
        val innerRegex = Regex("[0-9]{1,3}")
        occurrences.forEach { occurrence ->
            innerRegex.findAll(occurrence).forEach { result ->
                calculationInput.add(result.value.toInt())
            }
        }
        calculationInput.windowed(2, 2) { (a, b) ->
            calculation += a * b
        }

        return calculation
    }

    fun part2(input: List<String>): Int {
        fun calculateFromString(string: String): Int {
            val occurrences = ArrayList<String>()
            val calculationInput = ArrayList<Int>()
            var calculation = 0

            val outerRegex = Regex("mul\\([0-9]{1,3},[0-9]{1,3}\\)")
            outerRegex.findAll(string).forEach { result ->
                occurrences.add(result.groupValues[0])
            }
            val innerRegex = Regex("[0-9]{1,3}")
            occurrences.forEach { occurrence ->
                innerRegex.findAll(occurrence).forEach { result ->
                    calculationInput.add(result.value.toInt())
                }
            }
            calculationInput.windowed(2, 2) { (a, b) ->
                calculation += a * b
            }

            return calculation
        }

        val joined = input.joinToString()
        var calculation = 0

        val sequences = joined.split("do")
        sequences.forEachIndexed { index, sequence ->
            if (index == 0) {
                calculation += calculateFromString(sequences[0])
            }
            when {
                sequence.startsWith("()") -> calculation += calculateFromString(sequence)
                else -> { }
            }
        }


//        input.forEachIndexed { index, line ->
//            val sequences = line.split("do")
//            val doFromLastLine = true
//            if (index == 0) {
//                calculation += calculateFromString(sequences[0])
//            }
//            sequences.forEach { sequence ->
//                when {
//                    sequence.startsWith("()") -> calculation += calculateFromString(sequence)
//                    else -> { }
//                }
//            }
//        }
        return calculation
    }

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
