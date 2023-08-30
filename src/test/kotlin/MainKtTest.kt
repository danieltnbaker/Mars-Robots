import model.Direction
import model.Position
import kotlin.test.Test
import kotlin.test.assertEquals

class MainKtTest {

    @Test
    fun `test rotateAntiClockwise returns correct direction`() {
        assertEquals(rotateAntiClockwise(Position(1, 2, Direction.NORTH)).direction, Direction.WEST)
        assertEquals(rotateAntiClockwise(Position(1, 2, Direction.EAST)).direction, Direction.NORTH)
        assertEquals(rotateAntiClockwise(Position(1, 2, Direction.SOUTH)).direction, Direction.EAST)
        assertEquals(rotateAntiClockwise(Position(1, 2, Direction.WEST)).direction, Direction.SOUTH)
    }

    @Test
    fun `test rotateClockwise returns correct direction`() {
        assertEquals(rotateClockwise(Position(1, 2, Direction.NORTH)).direction, Direction.EAST)
        assertEquals(rotateClockwise(Position(1, 2, Direction.EAST)).direction, Direction.SOUTH)
        assertEquals(rotateClockwise(Position(1, 2, Direction.SOUTH)).direction, Direction.WEST)
        assertEquals(rotateClockwise(Position(1, 2, Direction.WEST)).direction, Direction.NORTH)
    }

    @Test
    fun `test moveForward moves robot correct direction`() {
        val position = Position(1, 2, Direction.NORTH)
        assertEquals(moveForward(position, 3, 4), Position(1, 3, Direction.NORTH))
        assertEquals(moveForward(position.copy(direction = Direction.EAST), 3, 4), Position(2, 2, Direction.EAST))
        assertEquals(moveForward(position.copy(direction = Direction.SOUTH), 3, 4), Position(1, 1, Direction.SOUTH))
        assertEquals(moveForward(position.copy(direction = Direction.WEST), 3, 4), Position(0, 2, Direction.WEST))
    }

    @Test
    fun `test moveForward shows lost when reaching maxX`() {
        assertEquals(
            moveForward(Position(3, 2, Direction.EAST), 3, 3),
            Position(3, 2, Direction.EAST, true)
        )
        assertEquals(
            moveForward(Position(0, 1, Direction.WEST), 3, 3),
            Position(0, 1, Direction.WEST, true)
        )
    }

    @Test
    fun `test moveForward shows lost when reaching maxY`() {
        assertEquals(
            moveForward(Position(1, 3, Direction.NORTH), 3, 3),
            Position(1, 3, Direction.NORTH, true)
        )
        assertEquals(
            moveForward(Position(2, 0, Direction.SOUTH), 3, 3),
            Position(2, 0, Direction.SOUTH, true)
        )
    }

    @Test
    fun `test printPosition shows correct end position`() {
        assertEquals(
            printPosition(Position(1, 3, Direction.NORTH)),
            "1 3 N"
        )
    }

    @Test
    fun `test printPosition shows correct end position with lost message`() {
        assertEquals(
            printPosition(Position(3, 2, Direction.EAST, true)),
            "3 2 E LOST"
        )
    }
}