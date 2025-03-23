package ca.mcmaster.se2aa4.island.teamXXX;
import ca.mcmaster.se2aa4.island.teamXXX.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;
import java.util.Map;

import org.json.JSONArray;
import java.util.ArrayList;

public class Control {

    private final Logger logger = LogManager.getLogger();
    Drone drone;
    MapRepresenter map;

    Storage storage = new Storage();
    Decisions current;
    Mapping mapping;

    Control(Drone drone, MapRepresenter map){
        this.drone = drone;
        this.map = map;
        this.mapping = new Mapping(drone, map);
        this.current = new Echo(mapping);
    }

    /*this method is where everything happens for this rescue mission. Interface between our objects and classes, and the explorer class
    We start by initializing the map and finding ground
    then we want to go through the coast line and find the creeks
    then we want to implement a grid search system to find the emergency sites
    We will make classes for all of these things and use nextDecision to implement them
    */
    public String nextDecision() {

        logger.info("Battery Level: " + drone.getBatteryLevel());

        if (drone.getBatteryLevel() < 50) {
            return drone.stop();
        }

        if (storage.getCost() != null) {
            if (drone.getAction().equals("scan")) {
                map.storeScanResults(storage, drone.getCurrentLocation());
            }
            if (current instanceof ProcessDecisions) {
                ((ProcessDecisions) current).processResponse(storage, drone, map);
            }
        }

        while (!current.isFinal()) {
            while (!current.isReached()) {
                String decision = current.nextDecision( drone, map);
                if (!(decision == null)) {
                    return decision;
                }
            }
            this.current = current.getStage();
        }
        return drone.stop();
    }

    public void storeResponse(String action, JSONObject previousResponse) {
        storage.storeResponse(action, previousResponse);
    }    

    
}
