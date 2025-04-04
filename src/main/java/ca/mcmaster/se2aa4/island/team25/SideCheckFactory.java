package ca.mcmaster.se2aa4.island.team25;

public class SideCheckFactory extends SearchFactory{

    @Override
    public SearchMethod createSearch(Drone drone, boolean flipped) {
        // TODO Auto-generated method stub
        return new SideCheck(drone, flipped);
    }
    
}
