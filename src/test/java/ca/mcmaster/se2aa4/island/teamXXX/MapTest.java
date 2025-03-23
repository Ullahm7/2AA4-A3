package ca.mcmaster.se2aa4.island.teamXXX;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.island.team25.InterestPoint;
import ca.mcmaster.se2aa4.island.team25.Kind;
import ca.mcmaster.se2aa4.island.team25.ListMap;

public class MapTest {
    private ListMap MapTest;
    private InterestPoint creek1, creek2, ePoint;

    @BeforeEach
    public void preCondition() {
        MapTest = new ListMap();
    }

    @Test
    public void testAdding() {
        MapTest.putPoint("1", 20, 22, Kind.Creek);
        MapTest.putPoint("2", 30, 42, Kind.Creek);
        MapTest.putPoint("3", 25, 32, Kind.EmergencySite);
        assertEquals(MapTest.returnEmergencyPoint().returnID(), "3");
    }

    @Test
    public void testSorting() {
        MapTest.putPoint("1", 20, 22, Kind.Creek);
        MapTest.putPoint("2", 30, 42, Kind.Creek);
        MapTest.putPoint("4", 48, 32, Kind.Creek);
        MapTest.putPoint("3", 25, 32, Kind.EmergencySite);
        MapTest.putPoint("5", 26, 30, Kind.Creek);
        MapTest.sortPoint();
        assertEquals("4", MapTest.returnCloseInterestPoint().returnID());
    }
}
