package ca.mcmaster.se2aa4.island.team25;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class UTurn implements SearchMethod {

    private final Logger logger = LogManager.getLogger();
    private Drone drone;

    private int counter = -1;

    private boolean echoSide = false;

    public UTurn(Drone drone) {
        this.drone = drone;
    }

    @Override
    public JSONObject nextStep() {
        logger.info("*** CURRENTLY IN UTURN");
        return this.drone.simpleAction(Action.STOP);
    }

    @Override
    public void giveInfo(JSONObject info) {
        
    }

    @Override
    public SearchMethod searchType() {
        return this;
    }
}
