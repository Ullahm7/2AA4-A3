package ca.mcmaster.se2aa4.island.teamXXX;

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

    Boolean initialEchoed = false;
    Boolean gridSearch = false;
    Boolean searchedCoast = false;
    Boolean stop = false;

    Storage storage = new Storage();
    Initializer initializer;
    GridSearcher gridSearcher;
    Mapping mapping;
    Decisions current;

    Control(Drone drone, MapRepresenter map){
        this.drone = drone;
        this.map = map;
        this.initializer = new Initializer(drone, map);
        this.gridSearcher = new GridSearcher(drone, map);
        this.mapping = new Mapping(map);
        this.current = new Echo(mapping);
    }

    /*this method is where everything happens for this rescue mission. Interface between our objects and classes, and the explorer class
    We start by initializing the map and finding ground
    then we want to go through the coast line and find the creeks
    then we want to implement a grid search system to find the emergency sites
    We will make classes for all of these things and use nextDecision to implement them
    */
    public String nextDecision() {

        if (drone.getBatteryLevel() < 50){
            return drone.stop();
        }

        //first echo to determine where the drone is located
        if (initialEchoed == false) {
            initialEchoed = true;
            return drone.echo(this.drone.initialHeading);
        }

        if (drone.getAction().equals("scan")) {
            map.storeScanResults(storage, drone.currentLocation);
        }

        while (!current.isFinal()){
            while (!current.isReached()){
                current.processResponse(storage, drone, map);
                String decision = current.nextDecision(storage, drone, map);
                if (!(decision == null)){
                    return decision;
                }
                /// handler.process(decision); questionable
            }
            this.current = current.getStage();
        }


        //initializatoin and finding ground
        if (map.initialized == false) {
            return initializer.initializeMission(this.drone.initialHeading, storage);
        }


        if (map.initialized == true && gridSearch == false) {
            return gridSearcher.searchGrid(storage);
        }

        logger.info("MAP INITIALIZED");
        return drone.stop();
    }

    public void storeResponse(String action, JSONObject previousResponse){
        // want to clear at the start of each iteration
        storage.clear();

        List<String> temp = new ArrayList<String>();
        storage.setCost(previousResponse.getInt("cost"));

        if (action.equals("echo")) {
            storage.setRange(previousResponse.getJSONObject("extras").getInt("range"));
            storage.setFound(previousResponse.getJSONObject("extras").getString("found"));
        }

        // store as lists with first item being null if empty
        else if (action.equals("scan")) {
            temp = new ArrayList<String>();
            JSONArray creeksArray = previousResponse.getJSONObject("extras").getJSONArray("creeks");
            if (creeksArray.length() == 0) {
                temp.add("null");
            } else {

                for (int i = 0; i < creeksArray.length(); i++) {
                    temp.add(creeksArray.getString(i));
                }
            }
            storage.setCreeks(temp);

            temp = new ArrayList<String>();
            JSONArray biomesArray = previousResponse.getJSONObject("extras").getJSONArray("biomes");
            if (biomesArray.length() == 0) {
                temp.add("null");
            } else {

                for (int i = 0; i < biomesArray.length(); i++) {
                    temp.add(biomesArray.getString(i));
                }
            }
            storage.setBiomes(temp);

            // assuming there is only one site, we only store the first value
            temp = new ArrayList<String>();
            JSONArray sitesArray = previousResponse.getJSONObject("extras").getJSONArray("sites");
            if (sitesArray.length() == 0) {
                temp.add("null");
            } else {
                temp.add(sitesArray.getString(0));
            }
            storage.setSite(temp.get(0));

        }
    }    

    
}
