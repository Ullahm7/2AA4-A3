package ca.mcmaster.se2aa4.island.team25;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class StraightLine implements SearchMethod {

    private final Logger logger = LogManager.getLogger();
    private Drone drone;

    private int counter = -1;
    private boolean scan = false;

    private boolean echoAhead = true;

    public StraightLine(Drone drone) {
        logger.info("*** STARTING A STRAIGHT ***");
        this.drone = drone;
    }

    @Override
    public JSONObject nextStep() {
        logger.info("***STRAIGHT NEXT STEP || " + (this.counter+1));
        this.counter += 1;

        if (this.counter % 4 == 0) {
            return this.drone.radarDirection(this.drone.currentDir());
        }

        if (this.scan) {
            this.scan = false;
            return this.drone.simpleAction(Action.SCAN);
        } else {
            this.scan = true;
            return this.drone.simpleAction(Action.FLY);
        }
    }

    @Override
    public void giveInfo(JSONObject info) {
        if (info.has("found")) {
            this.echoAhead = "GROUND".equals(info.getString("found"));
        }
    }

    @Override
    public SearchMethod searchType() {
        if (!this.echoAhead) {
            return new SideCheck(this.drone);
        }
        return this;
    }
}
