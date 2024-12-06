fun main() {
    fun part1(input: List<String>): Int {
        val width = input[0].length
        val height = input.size
        val position = mutableListOf(0, 0)
        var direction = '^'
        val initialLine = input.first { it.contains(direction) }
        position[0] = input.indexOf(initialLine)
        position[1] = initialLine.indexOfFirst { it == direction }

        val distinctPositions = mutableSetOf(Pair(position[0], position[1]))

        while (position[0] < (width - 1) && position[1] < (height - 1)) {
            if (direction == '^') {
                if (input[position[0] - 1][position[1]] == '#') {
                    direction = '>'
                    distinctPositions.add(Pair(position[0], position[1]))
                    position[1] = position[1] + 1
                    continue
                } else {
                    distinctPositions.add(Pair(position[0], position[1]))
                    position[0] = position[0] - 1
                    continue
                }
            }
            if (direction == '>') {
                if (input[position[0]][position[1] + 1] == '#') {
                    direction = 'v'
                    distinctPositions.add(Pair(position[0], position[1]))
                    position[0] = position[0] + 1
                    continue
                } else {
                    distinctPositions.add(Pair(position[0], position[1]))
                    position[1] = position[1] + 1
                    continue
                }
            }
            if (direction == 'v') {
                if (input[position[0] + 1][position[1]] == '#') {
                    direction = '<'
                    distinctPositions.add(Pair(position[0], position[1]))
                    position[1] = position[1] - 1
                    continue
                } else {
                    distinctPositions.add(Pair(position[0], position[1]))
                    position[0] = position[0] + 1
                    continue
                }
            }
            else {
                if (input[position[0]][position[1] - 1] == '#') {
                    direction = '^'
                    distinctPositions.add(Pair(position[0], position[1]))
                    position[0] = position[0] - 1
                    continue
                } else {
                    distinctPositions.add(Pair(position[0], position[1]))
                    position[1] = position[1] - 1
                    continue
                }
            }

        }
        return distinctPositions.size + 1
    }

//    fun part2(input: List<String>): Int {
//
//    }

    val input = readInput("Day06")
    part1(input).println()
//    part2(input).println()
}

private operator fun String.set(i: Int, value: Char) {
    this[i] = value
}
