package ca.mcmaster.se2aa4.island.team25;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class UTurn implements SearchMethod {

    private final Logger logger = LogManager.getLogger();
    private Drone drone;

    private int turnCount = -1;
    private boolean isNorth;
    private boolean flipped;

    public UTurn(Drone drone, boolean flipped) {
        this.drone = drone;
        this.flipped = flipped;
        this.isNorth = (this.drone.currentDir() == Direction.NORTH);
        if (flipped) {
            this.isNorth = !this.isNorth;
        }
    }

    @Override
    public JSONObject nextStep() {

        //logger.info("*** IN UTURN");
       
        if (turnCount == -1) {
            this.turnCount++;
            return this.drone.radarDirection(this.drone.currentDir());
        }
        if (turnCount > 1) {
            this.turnCount++;
            return this.drone.simpleAction(Action.SCAN);
        }
        if (this.isNorth) {
            this.turnCount++;
            return this.drone.turnRight();
        } else {
            this.turnCount++;
            return this.drone.turnLeft();
        }
    }

    @Override
    public void giveInfo(JSONObject info) {
        
    }

    @Override
    public SearchMethod searchType() {
        if (drone.goHome()) {
            return new FindHome(this.drone);
        }
        if (turnCount < 2) {
            return this;
        }
        return new StraightLine(this.drone, this.flipped);
    }
}
