package ca.mcmaster.se2aa4.island.team25.Factories;

import ca.mcmaster.se2aa4.island.team25.Drone;
import ca.mcmaster.se2aa4.island.team25.SearchMethod;

public abstract class SearchFactory {

    public abstract SearchMethod createSearch(Drone drone, boolean flipped);
    
}
