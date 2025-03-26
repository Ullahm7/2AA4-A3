package ca.mcmaster.se2aa4.island.team25;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DroneTest {

    private Drone drone;

    @BeforeEach
    public void initializeDroneBatteryAndHeading() {
        drone = new Drone(100, 'N'); 
    }

    @Test
    public void testSufficientBatteryForSearch() {
        drone.setCoordinate(3, 4);
        assertFalse(drone.goHome());
    }

    @Test
    public void testInsufficientBatteryForSearch() {
        drone.batteryLost(73); 
        drone.setCoordinate(3, 4); 
        assertTrue(drone.goHome());
    }

}
