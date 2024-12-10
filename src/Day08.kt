enum class Direction {RIGHT, LEFT}

data class Vector(val x: Int, val y: Int) {
    operator fun minus(other: Vector) = Vector(x - other.x, y - other.y)
    operator fun plus(other: Vector) = Vector(x + other.x, y + other.y)
    operator fun times(scalar: Int) = Vector(x * scalar, y * scalar)
}

fun main() {
    fun part1(input: List<String>): Int {
        val height = input.size
        val width = input[0].length

        val antennas = buildMap<Char, MutableSet<Vector>> {
            for (y in 0..<height) {
                for (x in 0..<width) {
                    val currentChar = input[y][x]
                    if (currentChar == '.') continue
                    val antennasAtChar = this.getOrElse(currentChar) { mutableSetOf() }
                    antennasAtChar += Vector(x, y)
                    this[currentChar] = antennasAtChar
                }
            }
        }

        val antinodes = mutableSetOf<Vector>()
        for (antennaType in antennas.keys) {
            for (antenna in antennas[antennaType]!!) {
                for (otherAntenna in antennas[antennaType]!!) {
                    if (antenna == otherAntenna) continue
                    val distance = otherAntenna - antenna
                    val relativeDistance = distance * 2
                    val antinode = antenna + relativeDistance
                    if (antinode.x in 0..<width && antinode.y in 0..<height) {
                        antinodes.add(antinode)
                    }
                }
            }
        }

        return antinodes.size
    }


    fun part2(input: List<String>): Int {
        val height = input.size
        val width = input[0].length

        val antennas = buildMap<Char, MutableSet<Vector>> {
            for (y in 0..<height) {
                for (x in 0..<width) {
                    val currentChar = input[y][x]
                    if (currentChar == '.') continue
                    val antennasAtChar = this.getOrElse(currentChar) { mutableSetOf() }
                    antennasAtChar += Vector(x, y)
                    this[currentChar] = antennasAtChar
                }
            }
        }

        val antinodes = mutableSetOf<Vector>()
        for (antennaType in antennas.keys) {
            for (antenna in antennas[antennaType]!!) {
                for (otherAntenna in antennas[antennaType]!!) {
                    if (antenna == otherAntenna) continue
                    val distance = otherAntenna - antenna
                    val relativeDistance = distance
                    var positiveAntinode = antenna
                    do {
                        positiveAntinode = positiveAntinode + relativeDistance
                        if (positiveAntinode.x in 0..<width && positiveAntinode.y in 0..<height) {
                            antinodes.add(positiveAntinode)
                        }
                    } while (positiveAntinode.x in 0..<width && positiveAntinode.y in 0..<height)
                    var negativeAntinode = antenna
                    do {
                        negativeAntinode = negativeAntinode - relativeDistance
                        if (negativeAntinode.x in 0..<width && negativeAntinode.y in 0..<height) {
                            antinodes.add(negativeAntinode)
                        }
                    } while (positiveAntinode.x in 0..<width && positiveAntinode.y in 0..<height)
                }
            }
        }

        return antinodes.size
    }

    val input = readInput("Day08")
    part1(input).println()
    part2(input).println()
}
