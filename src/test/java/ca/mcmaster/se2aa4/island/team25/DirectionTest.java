package ca.mcmaster.se2aa4.island.team25;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DirectionTest {

    private final Direction eastFace = Direction.EAST;
    private final Direction northFace = Direction.NORTH;

    @Test
    public void turnLeft() {
        assertEquals(Direction.WEST, northFace.turnLeft());
        assertEquals(Direction.NORTH, eastFace.turnLeft());
    }

    @Test
    public void turnRight() {
        assertEquals(Direction.EAST, northFace.turnRight());
        assertEquals(Direction.SOUTH, eastFace.turnRight());
    }

    @Test
    public void symbolTest() {
        assertEquals(Direction.NORTH.icon, 'N');
        assertEquals(Direction.EAST.icon, 'E');
        assertEquals(Direction.WEST.icon, 'W');
        assertEquals(Direction.SOUTH.icon, 'S');
    }

    @Test
    public void getIcon() {
        assertEquals("E", eastFace.getIcon());
    }
}
