import ext.getAction
import ext.getDirection
import ext.getX
import ext.getY
import model.Action
import model.Direction
import model.Position

fun main() {
    val maxCoordinates = readln()
    val maxX = maxCoordinates.getX()
    val maxY = maxCoordinates.getY()

    while (true) {
        val startingPositionInput = readln()

        var currentPosition = Position(
            startingPositionInput.getX(),
            startingPositionInput.getY(),
            startingPositionInput.getDirection() ?: Direction.NORTH
        )

        val actions = readln()
        run breaking@{
            actions.forEach { action ->
                when (action.toString().getAction()) {
                    Action.LEFT -> rotateAntiClockwise(currentPosition).also { currentPosition = it }
                    Action.RIGHT -> rotateClockwise(currentPosition).also { currentPosition = it }
                    Action.FORWARD -> moveForward(currentPosition, maxX, maxY).also { if (it.isLost) return@breaking }
                    else -> {
                        // Error with input or new action we need to handle
                    }
                }
            }
        }

        println(printPosition(currentPosition))
    }
}

fun rotateAntiClockwise(position: Position): Position = position.copy(
    direction = when (position.direction) {
        Direction.NORTH -> Direction.WEST
        Direction.EAST -> Direction.NORTH
        Direction.SOUTH -> Direction.EAST
        Direction.WEST -> Direction.SOUTH
    }
)

fun rotateClockwise(position: Position): Position = position.copy(
    direction = when (position.direction) {
        Direction.NORTH -> Direction.EAST
        Direction.EAST -> Direction.SOUTH
        Direction.SOUTH -> Direction.WEST
        Direction.WEST -> Direction.NORTH
    }
)

fun moveForward(position: Position, maxX: Int, maxY: Int): Position {
    val newX = when (position.direction) {
        Direction.EAST -> position.x + 1
        Direction.WEST -> position.x - 1
        else -> position.x
    }
    val newY = when (position.direction) {
        Direction.NORTH -> position.y + 1
        Direction.SOUTH -> position.y - 1
        else -> position.y
    }

    if (newX in 0..maxX && newY in 0..maxY) {
        return position.copy(x = newX, y = newY)
    }
    return position.copy(isLost = true)
}

fun printPosition(position: Position) = if (position.isLost) {
    "${position.x} ${position.y} ${position.direction.initial} LOST"
} else {
    "${position.x} ${position.y} ${position.direction.initial}"
}