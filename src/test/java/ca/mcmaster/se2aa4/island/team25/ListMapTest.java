package ca.mcmaster.se2aa4.island.team25;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListMapTest {

    private ListMap listMap;

    @BeforeEach
    public void setUp() {
        listMap = new ListMap();
    }

    @Test
    public void testAddEmergencySite() {
      
        listMap.putPoint("EmergencySite1", 5, 5, Kind.EmergencySite);
        
        String emergencySite = listMap.returnEmergencyPoint();
        assertEquals("\nEMERGENCY SITE ID EmergencySite1", emergencySite);
    }

    @Test
    public void testReturnClosestCreek() {
   
        listMap.putPoint("Creek1", 10, 10, Kind.Creek);
        listMap.putPoint("Creek2", 2, 2, Kind.Creek);
        listMap.putPoint("EmergencySite", 1, 1, Kind.EmergencySite);
        
        listMap.sortPoint();

        assertEquals("Creek2", listMap.returnCloseInterestPoint());
    }
    
}
