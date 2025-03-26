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
    
}
