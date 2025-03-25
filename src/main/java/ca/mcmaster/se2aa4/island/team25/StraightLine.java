package ca.mcmaster.se2aa4.island.team25;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class StraightLine implements SearchMethod {

    private final Logger logger = LogManager.getLogger();
    private Drone drone;

    private int counter = -1;
    private boolean scan = true;
    
    private boolean checkEnd = true;
    private boolean isEnd = false;

    private boolean echoAhead = true;

    public StraightLine(Drone drone) {
        logger.info("*** STARTING A STRAIGHT ***");
        this.drone = drone;
    }

    @Override
    public JSONObject nextStep() {
        logger.info("***STRAIGHT NEXT STEP || " + (this.counter+1));
        this.counter += 1;

        if (counter == 0) {
            return this.drone.radarDirection(this.drone.currentDir());
        }

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
        logger.info("*** Check response");
        if (info.has("found")) {
            if (this.checkEnd) {
                this.checkEnd = false;
                this.isEnd = "GROUND".equals(info.getString("found"));
            }
            this.echoAhead = "GROUND".equals(info.getString("found"));
        }
    }

    @Override
    public SearchMethod searchType() {
        logger.info("STRAIGHT SEARCH RETURN ***");
        if (!this.isEnd) {
            return new fullUTurn(this.drone);
        }
        if (!this.echoAhead) {
            return new SideCheck(this.drone);
        }
        return this;
    }
}
