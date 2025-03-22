package ca.mcmaster.se2aa4.island.team25;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class CoastSearch implements SearchMethod {

    private final Logger logger = LogManager.getLogger();
    private boolean UTurn = false;
    private Drone drone;

    public CoastSearch(Drone drone) {
        this.drone = drone;
    }
    public JSONObject nextStep() {
        return this.drone.simpleAction(Action.FLY);
    }

    public void giveInfo(JSONObject info) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}