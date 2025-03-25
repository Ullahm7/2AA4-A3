package ca.mcmaster.se2aa4.island.team25;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class fullUTurn implements SearchMethod {

    private final Logger logger = LogManager.getLogger();
    private Drone drone;

    private int counter = 0;
    private boolean isNorth;

    public fullUTurn(Drone drone) {
        this.drone = drone;
        this.isNorth = (this.drone.currentDir() == Direction.NORTH);
    }

    @Override
    public JSONObject nextStep() {
        logger.info("*** STOPPING ***" + (this.counter+1));
        this.counter++;
        if (this.isNorth) {
            switch (this.counter) {
                case (1):
                    return this.drone.turnRight();
                case (2):
                    return this.drone.turnRight();
                case (3):
                    return this.drone.turnRight();
                case (4):
                    return this.drone.simpleAction(Action.FLY);
                case (5):
                    return this.drone.turnRight();
                default:
                    return this.drone.simpleAction(Action.STOP);
            }
        } else {
            switch (this.counter) {
                case (1):
                    return this.drone.turnLeft();
                case (2):
                    return this.drone.turnLeft();
                case (3):
                    return this.drone.turnLeft();
                case (4):
                    return this.drone.simpleAction(Action.FLY);
                case (5):
                    return this.drone.turnLeft();
                default:
                    return this.drone.simpleAction(Action.STOP);
            }
        }
    }

    @Override
    public void giveInfo(JSONObject info) {
    }

    @Override
    public SearchMethod searchType() {
        if (this.counter == 5) {
            return new StraightLine(this.drone);
        }
        return this;
    }
}
