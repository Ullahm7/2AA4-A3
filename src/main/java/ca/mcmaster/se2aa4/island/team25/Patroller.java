package ca.mcmaster.se2aa4.island.team25;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class Patroller {

    private final Logger logger = LogManager.getLogger();

    private ListMap mainMap = new ListMap();
    private JSONObject lastResponse;
    Drone drone;
    SearchMethod currentSearch;

    Patroller(Drone drone, ListMap map) {
        this.drone = drone;
        this.currentSearch = new InitialSearch(drone);
    }

    public String nextAction() {
        this.currentSearch = currentSearch.searchType();
        
        //this.mainMap.printCreeks();
        //this.mainMap.printEmergency();

        JSONObject action = new JSONObject();
        action = currentSearch.nextStep();
        return action.toString();
    }

    public void readAction(JSONObject cost, JSONObject info) {
        drone.batteryLost(cost.getInt("cost"));
        currentSearch.giveInfo(info);

        if (info.has("creeks")) {
            JSONArray creeks = info.getJSONArray("creeks");
            JSONArray sites = info.getJSONArray("sites");

            for (int i = 0; i < creeks.length(); i++) {
                this.mainMap.putPoint(creeks.getString(i),this.drone.getX(), this.drone.getY(),Kind.Creek);
            }

            for (int i = 0; i < sites.length(); i++) {
                this.mainMap.putPoint(sites.getString(i),this.drone.getX(), this.drone.getY(),Kind.EmergencySite);
            }

        }

    }
}
