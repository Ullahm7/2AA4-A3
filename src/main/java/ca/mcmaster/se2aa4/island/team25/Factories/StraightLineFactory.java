package ca.mcmaster.se2aa4.island.team25.Factories;

import ca.mcmaster.se2aa4.island.team25.Drone;
import ca.mcmaster.se2aa4.island.team25.SearchMethod;
import ca.mcmaster.se2aa4.island.team25.Searches.*;

public class StraightLineFactory extends SearchFactory{

    public SearchMethod createSearch(Drone drone, boolean flipped) {
        
        return new StraightLine(drone, flipped);
    }
    
}
