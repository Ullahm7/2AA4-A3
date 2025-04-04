package ca.mcmaster.se2aa4.island.team25;

public class UTurnFactory extends SearchFactory{

    @Override
    public SearchMethod createSearch(Drone drone, boolean flipped) {

        return new UTurn(drone, flipped);
        
    }
    
}
