package ca.mcmaster.se2aa4.island.team25;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

public class InterestPointTest {
    private InterestPoint IP1;
    private InterestPoint IP2;

    @BeforeEach
    public void initialize() {
        IP1 = new InterestPoint("1", 20, 30, Kind.Creek);
        IP2 = new InterestPoint("2", 70, 15, Kind.EmergencySite);
    }

    @Test
    public void getID() {
        assertEquals("1", IP1.returnID());
        assertEquals("2", IP2.returnID());
    }

    @Test
    public void getType() {
        assertEquals(Kind.Creek, IP1.getType());
        assertEquals(Kind.EmergencySite, IP2.getType());
    }

    @Test
    public void getCoordinates() {
        assertEquals(30, IP1.getY());
        assertEquals(20, IP1.getX());

        assertEquals(15, IP2.getY());
        assertEquals(70, IP2.getX());
    }

    @Test
    public void testDistance() { // Rounded for the sake of testing
        assertEquals(52.20153254455275, IP1.distanceTo(IP2));
    }

}
