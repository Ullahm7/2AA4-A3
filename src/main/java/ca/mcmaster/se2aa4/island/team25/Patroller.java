package ca.mcmaster.se2aa4.island.team25;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class Patroller {

    private final Logger logger = LogManager.getLogger();

    private JSONObject lastResponse;
    Drone drone;
    SearchMethod currentSearch;

    Patroller(Drone drone, Map map) {
        this.drone = drone;
        this.currentSearch = new InitialSearch(drone);
    }

    public String nextAction() {
        JSONObject action = new JSONObject();

        action = currentSearch.nextStep();
        logger.info(action.toString());
        return action.toString();
    }

    public void readAction(JSONObject response) {
        drone.batteryLost(response.getInt("cost"));
        currentSearch.giveInfo(response);
    }
}
