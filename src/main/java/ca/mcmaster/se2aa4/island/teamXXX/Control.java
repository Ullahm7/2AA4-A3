package ca.mcmaster.se2aa4.island.teamXXX;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.ArrayList;

public class Control {

    private final Logger logger = LogManager.getLogger();
    Drone drone;
    MapRepresenter map;
    HashMap<String, List<String>> responseStorage = new HashMap<String, List<String>>();

    Control(Drone drone, MapRepresenter map){
        this.drone = drone;
        this.map = map;
    }
    // starts the mission, need to first initialize the control, e.g map and the drone
    public String nextDecision() {
        return "null";
        
    }
    
}
