package ca.mcmaster.se2aa4.island.team25;

public class FindHomeFactory extends SearchFactory{

    @Override
    public SearchMethod createSearch(Drone drone, boolean flipped) {
        
        return new FindHome(drone);
    }
    
}
