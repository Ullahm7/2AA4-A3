package ca.mcmaster.se2aa4.island.teamXXX;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.island.team25.InterestPoint;
import ca.mcmaster.se2aa4.island.team25.Kind;

public class MapTest {
    private ArrayList<InterestPoint> mainPoints;
    private InterestPoint creek1, creek2, ePoint;

    @BeforeEach
    public void preCondition() {
        mainPoints = new ArrayList<InterestPoint>();
        creek1 = new InterestPoint("1", 20, 22, Kind.Creek);
        creek2 = new InterestPoint("2", 30, 42, Kind.Creek);
        ePoint = new InterestPoint("3", 25, 32, Kind.EmergencySite);
    }

    @Test
    public void testAdding() {

    }
}
