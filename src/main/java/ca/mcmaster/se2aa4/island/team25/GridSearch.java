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
    private int perfectTurn = 0;

    private boolean isDone = false;

    public GridSearch(Drone drone) {
        this.drone = drone;
    }

    public JSONObject nextStep() {
        stepNum++;

        if (checkAhead > 0) {
            if (this.checkAhead == 1) {
                this.checkAhead = 2;
                return this.drone.radarDirection(this.drone.currentDir());
            }

            if (!this.landAhead) {
                this.perfectTurn += 1;

                if (this.perfectTurn < 6) {
                    return this.drone.perfectUTurn(this.perfectTurn);
                } else {
                    this.isDone = true;
                    return this.drone.simpleAction(Action.SCAN);
                }
            }

            this.checkAhead = 0;
            return this.drone.simpleAction(Action.SCAN);

        } else if (!this.landAhead) {
            this.turning++;
            return this.drone.uTurn();

        } else if (stepNum % 6 == 0) {
            return this.drone.radarDirection(this.drone.currentDir());
        }

        scan = !scan;
        return drone.simpleAction(scan ? Action.SCAN : Action.FLY);
    }

    public void giveInfo(JSONObject info) {
        if (this.turning == 2 || this.perfectTurn == 5) {
            this.landAhead = true;
            this.turning = 0;
            this.checkAhead = 1;
            if (this.perfectTurn == 5) {
                this.perfectTurn++;
            }
        }
        if (info.has("found")) {
            this.landAhead = "GROUND".equals(info.getString("found"));
        }
    }

    public SearchMethod searchType() {
        if (!this.isDone && !drone.goHome()) {
            return this;
            
        }
        else {
            return new FindHome(this.drone);
        }
    }

}
