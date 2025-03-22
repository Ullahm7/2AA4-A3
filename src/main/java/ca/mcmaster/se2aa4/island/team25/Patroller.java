package ca.mcmaster.se2aa4.island.team25;

import org.json.JSONObject;

public class Patroller {

    private JSONObject lastResponse;
    boolean islandFound = false;

    Patroller(Drone drone, Map map) {
        
    }

    public String nextAction() {
        JSONObject action = new JSONObject();
        JSONObject extra = new JSONObject();

        if (lastResponse == null) {

        }

        return action.toString();
    }

    public void readAction(JSONObject response) {
    
    }
}
