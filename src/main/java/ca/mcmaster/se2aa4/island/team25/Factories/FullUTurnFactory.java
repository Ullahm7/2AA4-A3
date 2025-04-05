package ca.mcmaster.se2aa4.island.team25.Factories;

import ca.mcmaster.se2aa4.island.team25.Drone;
import ca.mcmaster.se2aa4.island.team25.Searches.*;
import ca.mcmaster.se2aa4.island.team25.SearchMethod;

public class FullUTurnFactory extends SearchFactory {

    public SearchMethod createSearch(Drone drone, boolean flipped) {
        
        return new FullUTurn(drone);
    }
    
}
