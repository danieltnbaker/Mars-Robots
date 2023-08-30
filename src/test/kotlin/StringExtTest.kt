import ext.getAction
import ext.getDirection
import ext.getX
import ext.getY
import model.Action
import model.Direction
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class StringExtTest {

    @Test
    fun `test getX gives correct X coordinate`() {
        val coordinate = "1 2 E"
        val x = coordinate.getX()
        assertTrue(x == 1)
    }

    @Test
    fun `test getY gives correct Y coordinate`() {
        val coordinate = "3 5 S"
        val y = coordinate.getY()
        assertTrue(y == 5)
    }

    @Test
    fun `test getDirection gives correct Direction`() {
        val coordinate = "1 7 N"
        val direction = coordinate.getDirection()
        assertEquals(direction, Direction.NORTH)
    }

    @Test
    fun `test getDirection gives null with bad Direction`() {
        val coordinate = "1 7 G"
        val direction = coordinate.getDirection()
        assertNull(direction)
    }

    @Test
    fun `test getAction gives correct Action`() {
        assertEquals("F".getAction(), Action.FORWARD)
        assertEquals("L".getAction(), Action.LEFT)
        assertEquals("R".getAction(), Action.RIGHT)
    }

    @Test
    fun `test getAction gives null with bad Action`() {
        assertNull("D".getAction())
        assertNull("1".getAction())
        assertNull("@".getAction())
    }
}