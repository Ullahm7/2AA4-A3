package ca.mcmaster.se2aa4.island.team25;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InitialSearchTest {
    private Drone drone;
    private InitialSearch initialSearch;

    @BeforeEach
    public void setUp() {
        drone = new Drone(100, 'N'); 
        initialSearch = new InitialSearch(drone);
    }

    @Test
    public void testNextStepWhenLandNotFound() {
        JSONObject action = initialSearch.nextStep();
        
        assertEquals("echo", action.getString("action"));
        
        assertEquals("E", action.getJSONObject("parameters").getString("direction"));
        
        action = initialSearch.nextStep();
        assertEquals("fly", action.getString("action"));
    }

    @Test
    public void testGiveInfoUpdatesLandFound() {
        JSONObject info = new JSONObject();
        info.put("found", "GROUND"); 
        info.put("range", 5); 

        initialSearch.giveInfo(info);

        assertTrue(initialSearch.landFound);
        assertEquals(2, initialSearch.distToIsland);
    }

    @Test
    public void testSearchTypeWhenSearchDone() {
        
        initialSearch.landFound = true; 
        initialSearch.distToIsland = 5; 

        initialSearch.searchDone = true;  

        SearchMethod nextSearch = initialSearch.searchType();
        assertTrue(nextSearch instanceof StraightLine);
    }

    @Test
    public void testSearchTypeWhenGoHome() {
        
        drone.batteryLost(90); 

        drone.setCoordinate(4, 2); 
        
        SearchMethod nextSearch = initialSearch.searchType();
        assertTrue(nextSearch instanceof FindHome);
    }
}
