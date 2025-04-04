package ca.mcmaster.se2aa4.island.team25;

public class InitialSearchFactory extends SearchFactory {

    @Override
    public SearchMethod createSearch(Drone drone, boolean flipped) {
        
        return new InitialSearch(drone);
    }
    
}
