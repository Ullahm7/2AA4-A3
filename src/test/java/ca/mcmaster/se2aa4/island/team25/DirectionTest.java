package ca.mcmaster.se2aa4.island.team25;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DirectionTest {

    private Direction droneCurrentDirectionNorth; 
    private Direction droneCurrentDirectionEast; 
    private Direction droneCurrentDirectionSouth; 
    private Direction droneCurrentDirectionWest;

    @BeforeEach
    public void initializeDroneDirectionalFace(){

        droneCurrentDirectionNorth = Direction.NORTH; 
        droneCurrentDirectionEast = Direction.EAST;   
        droneCurrentDirectionSouth = Direction.SOUTH;
        droneCurrentDirectionWest = Direction.WEST;

    }

    @Test
    public void testDirectionTurnRight() {

        assertEquals(Direction.EAST, droneCurrentDirectionNorth.turnRight());
        assertEquals(Direction.SOUTH, droneCurrentDirectionEast.turnRight());
        assertEquals(Direction.WEST, droneCurrentDirectionSouth.turnRight());
        assertEquals(Direction.NORTH, droneCurrentDirectionWest.turnRight());

    }


    @Test
    public void testDirectionTurnLeft() {

        assertEquals(Direction.WEST, droneCurrentDirectionNorth.turnLeft());
        assertEquals(Direction.NORTH, droneCurrentDirectionEast.turnLeft());
        assertEquals(Direction.EAST, droneCurrentDirectionSouth.turnLeft());
        assertEquals(Direction.SOUTH, droneCurrentDirectionWest.turnLeft());
        
    }
    
}
