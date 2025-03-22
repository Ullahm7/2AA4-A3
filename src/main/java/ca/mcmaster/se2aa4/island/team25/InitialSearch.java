package ca.mcmaster.se2aa4.island.team25;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class InitialSearch implements SearchMethod {

    private final Logger logger = LogManager.getLogger();

    private Drone drone;
    private int stepNum = -1;
    private boolean landFound = false;
    private int distToIsland = 0;
    private boolean turnToLand = false;

    public InitialSearch(Drone drone) {
        this.drone = drone;
    }

    public JSONObject nextStep() {
        stepNum += 1;
        if (!landFound) {
            if (stepNum % 2 == 0) {
                return this.drone.radarDirection(this.drone.currentDir().turnRight());
            } else {
                return this.drone.simpleAction(Action.FLY);
            }
        } else if (stepNum <= distToIsland) {
            if (!turnToLand) {
                this.turnToLand = true;
                return this.drone.turnRight();
            } else {
                return this.drone.simpleAction(Action.FLY);
            }
        } else if (stepNum == distToIsland+1) {
            return this.drone.simpleAction(Action.SCAN);
        } 
        else {
            return this.drone.simpleAction(Action.STOP);
        }

    }

    public void giveInfo(JSONObject info) {
        if (info.has("found")) {
            if ("GROUND".equals(info.getString("found"))) {
                this.landFound = true;
                this.distToIsland = info.getInt("range") + stepNum + 1;
            }
        }
    }
}
