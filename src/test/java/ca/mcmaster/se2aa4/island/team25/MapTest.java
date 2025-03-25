package ca.mcmaster.se2aa4.island.team25;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MapTest {
    private ListMap MapTest;

    @BeforeEach
    public void preCondition() {
        MapTest = new ListMap();
    }

    @Test
    public void testAdding() { // Tests adding ti=o the file
        MapTest.putPoint("1", 20, 22, Kind.Creek);
        MapTest.putPoint("2", 30, 42, Kind.Creek);
        MapTest.putPoint("3", 25, 32, Kind.EmergencySite);
        assertEquals("\nEMERGENCY SITE ID 3", MapTest.returnEmergencyPoint());
    }

    @Test
    public void testSorting() { // Tests the sorting function
        MapTest.putPoint("1", 20, 22, Kind.Creek);
        MapTest.putPoint("5", 26, 30, Kind.Creek);
        MapTest.putPoint("2", 303333, 423333, Kind.Creek);
        MapTest.putPoint("4", 48, 34, Kind.Creek);
        MapTest.putPoint("3", 25, 32, Kind.EmergencySite);
        MapTest.sortPoint();
        assertEquals("5", MapTest.returnCloseInterestPoint());
    }
}