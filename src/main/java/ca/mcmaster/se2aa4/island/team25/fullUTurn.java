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
        //logger.info("*** STOPPING ***" + (this.counter + 1));
        this.counter++;
        return this.counter == 4
                ? this.drone.simpleAction(Action.FLY)
                : this.counter == 6
                        ? this.drone.simpleAction(Action.STOP)
                        : (this.isNorth
                                ? this.drone.turnRight()
                                : this.drone.turnLeft());
    }

    @Override
    public void giveInfo(JSONObject info) {
    }

    @Override
    public SearchMethod searchType() {
        if (this.counter == 5) {
            return new StraightLine(this.drone, true);
        }
        return this;
    }
}
