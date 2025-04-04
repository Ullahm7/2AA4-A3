package ca.mcmaster.se2aa4.island.team25;

public abstract class SearchFactory {

    public abstract SearchMethod createSearch(Drone drone, boolean flipped);
    
}
