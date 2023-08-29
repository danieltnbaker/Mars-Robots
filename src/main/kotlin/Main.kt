private var currentDirection: Direction = Direction.NORTH
private var x: Int = 0
private var y: Int = 0
private var maxX: Int = 0
private var maxY: Int = 0

fun main() {
    val maxCoordinates = readln()
    // val maxCoordinates = "5 3"
    maxX = maxCoordinates.getX()
    maxY = maxCoordinates.getY()

    println("maxX is $maxX, maxY is $maxY")

    // val startingPosition = "1 1 E".replace(" ", "")
    // val startingPosition = "3 2 N".replace(" ", "")
    // val startingPosition = "0 3 W".replace(" ", "")
    val startingPosition = readln()
    x = startingPosition.getX()
    y = startingPosition.getY()
    currentDirection = startingPosition.getDirection()

    var isLost = false
    // val actions = "RFRFRFRF"
    // val actions = "FRRFLLFFRRFLL"
    // val actions = "LLFFFLFLFL"
    val actions = readln()
    run breaking@{
        actions.forEach {
            when (getAction(it.toString())) {
                Action.LEFT -> rotateAntiClockwise()
                Action.RIGHT -> rotateClockwise()
                Action.FORWARD -> if (!moveForward()) {
                    isLost = true
                    return@breaking
                }
            }
        }
    }
    val extra = if (isLost) " LOST" else ""
    println("$x $y ${currentDirection.initial}$extra")
}

private fun getAction(input: String): Action = Action.entries.first { it.action == input }

private fun rotateAntiClockwise() {
    currentDirection = when (currentDirection) {
        Direction.NORTH -> Direction.WEST
        Direction.EAST -> Direction.NORTH
        Direction.SOUTH -> Direction.EAST
        Direction.WEST -> Direction.SOUTH
    }
}

private fun rotateClockwise() {
    currentDirection = when (currentDirection) {
        Direction.NORTH -> Direction.EAST
        Direction.EAST -> Direction.SOUTH
        Direction.SOUTH -> Direction.WEST
        Direction.WEST -> Direction.NORTH
    }
}

private fun moveForward(): Boolean {
    val newX = when (currentDirection) {
        Direction.EAST -> x + 1
        Direction.WEST -> x - 1
        else -> x
    }
    val newY = when (currentDirection) {
        Direction.NORTH -> y + 1
        Direction.SOUTH -> y - 1
        else -> y
    }
    println("x is $x, y is $y, newX is $newX, newY is $newY")
    if (newX in 0..maxX && newY in 0 .. maxY) {
        x = newX
        y = newY
        return true
    }
    return false
}

fun String.getX() = trimSpace().substring(0, 1).toInt()
fun String.getY() = trimSpace().substring(1, 2).toInt()
fun String.getDirection() = Direction.entries.first { it.initial == trimSpace().substring(2, 3) }
fun String.trimSpace() = replace(" ", "")

enum class Action(val action: String) {
    LEFT("L"),
    RIGHT("R"),
    FORWARD("F")
}

enum class Direction(val initial: String) {
    NORTH("N"),
    EAST("E"),
    SOUTH("S"),
    WEST("W")
}