package ca.mcmaster.se2aa4.island.team25;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class SideCheck implements SearchMethod {

    private final Logger logger = LogManager.getLogger();
    private Drone drone;

    private boolean sideCheck = false;
    private boolean emptySide = false;
    public SideCheck(Drone drone) {
        this.drone = drone;
    }

    @Override
    public JSONObject nextStep() {
        logger.info("*** SIDE CHECK STEP");
        if (this.sideCheck) {
            this.sideCheck = !this.sideCheck;
            if (this.drone.currentDir() == Direction.NORTH) {
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
