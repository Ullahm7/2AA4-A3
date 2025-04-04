package ca.mcmaster.se2aa4.island.team25;

public class SideCheckFactory extends SearchFactory{

    public SearchMethod createSearch(Drone drone, boolean flipped) {
        
        return new SideCheck(drone, flipped);
    }
    
}
