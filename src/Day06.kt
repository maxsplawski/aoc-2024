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

    fun part2(input: List<String>): Int {
        val width = input[0].length
        val height = input.size
        val obstacles = mutableSetOf<Pair<Int, Int>>()
        var initialPosition = 0 to 0

        val directions = listOf(
            0 to -1, // top
            1 to 0, // right
            0 to 1, // bottom
            -1 to 0, // left
        )

        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                if (char == '#') obstacles.add(x to y)
                if (char == '^') initialPosition = x to y
            }
        }

        var looped = 0

        for (outerX in 0..<width) {
            for (outerY in 0..<height) {
                val newObstacles = obstacles + (outerX to outerY)

                var direction = directions.first()
                var position = initialPosition

                fun turnRight() {
                    val i = (directions.indexOf(direction) + 1) % directions.size
                    direction = directions[i]
                }

                fun nextPosition() = (position.first + direction.first) to (position.second + direction.second)

                val visited = mutableSetOf<Pair<Pair<Int, Int>, Pair<Int, Int>>>()
                while (position.first in 0..width && position.second in 0..height) {
                    if (position to direction in visited) {
                        looped++
                        break
                    }
                    visited += (position to direction)
                    val nextPosition = nextPosition()
                    if (nextPosition in newObstacles) {
                        turnRight()
                    } else {
                        position = nextPosition
                    }
                }
            }
        }

        return looped
    }

    val input = readInput("Day06_test")
    part1(input).println()
    part2(input).println()
}
