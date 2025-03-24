package ca.mcmaster.se2aa4.island.team25;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class GridSearch implements SearchMethod {

    private final Logger logger = LogManager.getLogger();

    private Drone drone;

    private int stepNum = -1;
    private boolean landAhead = true;
    private boolean scan = true;

    private int turning = 0;
    private int checkAhead = 0;

    public GridSearch(Drone drone) {
        this.drone = drone;
    }

    public JSONObject nextStep() {
        stepNum += 1;
        if (this.checkAhead == 1) {
            this.checkAhead = 2;
            return this.drone.radarDirection(this.drone.currentDir());
        } else if (this.checkAhead == 2) {
            if (!this.landAhead) {
                return this.drone.simpleAction(Action.STOP);
            }
            this.checkAhead = 0;
            return this.drone.simpleAction(Action.SCAN);
        } else if (!this.landAhead) {
            this.turning++;
            return this.drone.uTurn();
        } else if (stepNum % 6 == 0) {
            return this.drone.radarDirection(this.drone.currentDir());
        } else {
            if (this.scan) {
                this.scan = false;
                return this.drone.simpleAction(Action.SCAN);
            }
            this.scan = true;
            return this.drone.simpleAction(Action.FLY);
        }
    }

    public void giveInfo(JSONObject info) {
        if (this.turning == 2) {
            this.landAhead = true;
            this.turning = 0;
            this.checkAhead = 1;
        }
        if (info.has("found")) {
            this.landAhead = "GROUND".equals(info.getString("found"));
        }
    }

    public SearchMethod searchType() {
        return this;
    }

}
