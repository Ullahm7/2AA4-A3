package ca.mcmaster.se2aa4.island.team25;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class SideCheck implements SearchMethod {

    private final Logger logger = LogManager.getLogger();
    private Drone drone;
    private Direction currentDir;
    private boolean flipped;
    private boolean sideCheck = false;
    private boolean emptySide = false;
    public SideCheck(Drone drone, boolean flipped) {
        this.drone = drone;
        this.flipped = flipped;
        this.currentDir = this.drone.currentDir();
    }

    @Override
    public JSONObject nextStep() {
        if (this.flipped) {
            this.currentDir = this.currentDir.turnLeft().turnLeft();
        }
        logger.info("*** SIDE CHECK STEP");
        if (this.sideCheck) {
            this.sideCheck = !this.sideCheck;
            if (this.currentDir == Direction.NORTH) {
                return this.drone.radarDirection(this.drone.currentDir().turnRight());
            }
            return this.drone.radarDirection(this.drone.currentDir().turnLeft());
        }
        this.sideCheck = true;
        return this.drone.simpleAction(Action.FLY);
    }

    @Override
    public void giveInfo(JSONObject info) {
        if (info.has("found")) {
            this.emptySide = !("GROUND".equals(info.getString("found")) && info.getInt("range") < 3);
        }
    }

    @Override
    public SearchMethod searchType() {
        if (this.emptySide) {
            return new UTurn(this.drone);
        }
        return this;
    }
}
