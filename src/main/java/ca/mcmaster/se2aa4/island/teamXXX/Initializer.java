package ca.mcmaster.se2aa4.island.teamXXX;

import ca.mcmaster.se2aa4.island.teamXXX.Drone.Heading;
import ca.mcmaster.se2aa4.island.teamXXX.MapRepresenter;
import ca.mcmaster.se2aa4.island.teamXXX.Drone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;
import java.util.List;

// an object of this class is responsible for initializing the drone location and finding ground 
public class Initializer {

    private final Logger logger = LogManager.getLogger(); 

    Drone drone;
    MapRepresenter map;

    //variables to initialize the mapping 
    public int topY;
    public int bottomY;
    public int leftX;
    public int rightX;

    Initializer(Drone drone, MapRepresenter map){
        this.drone = drone;
        this.map = map;
    }

    public String initializeMission() {
        // TODO Auto-generated method stub
        return null;
    }


}
