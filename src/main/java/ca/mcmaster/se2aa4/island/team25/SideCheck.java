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
    private boolean frontCheck = true;
    private boolean fullTurn = false;

    public SideCheck(Drone drone, boolean flipped) {
        this.drone = drone;
        this.flipped = flipped;
        this.currentDir = this.drone.currentDir();
    }

    @Override
    public JSONObject nextStep() {
        if (this.flipped) {
            this.currentDir = this.drone.currentDir().turnLeft().turnLeft();
        }
        //logger.info("*** SIDE CHECK STEP WITH DIR: " + this.currentDir.toString() + " " + this.drone.currentDir().toString() + " " + this.flipped);
        if (this.sideCheck) {
            if (this.currentDir == Direction.NORTH) {
                return this.drone.radarDirection(this.drone.currentDir().turnRight());
            }
            return this.drone.radarDirection(this.drone.currentDir().turnLeft());
        } 
        else {
            if (!frontCheck) {
                return this.drone.simpleAction(Action.FLY);
            } else {
                return this.drone.radarDirection(this.drone.currentDir());
            }
        }

    }

    @Override
    public void giveInfo(JSONObject info) {
        if (info.has("found")) {
            if ("OUT_OF_RANGE".equals(info.getString("found")) && this.frontCheck && (info.getInt("range") < 3)) {
                this.fullTurn = true;
            } else if ("GROUND".equals(info.getString("found"))) {
                if (info.getInt("range") > 3) {
                    this.emptySide = true;
                }
            } else if (this.sideCheck && ("OUT_OF_RANGE".equals(info.getString("found")))) {
                this.emptySide = true;
            }
        }
        if (this.frontCheck && !this.sideCheck) {
            this.sideCheck = false;
            this.frontCheck = false;
        }
        else if (!this.frontCheck && !this.sideCheck) {
            this.sideCheck = true;
            this.frontCheck = false;
        }
        else {
            this.sideCheck = false;
            this.frontCheck = true;
        }
    }

    @Override
    public SearchMethod searchType() {
        if (drone.goHome()) {
            return new FindHome(this.drone);
        }
        if (this.fullTurn || this.emptySide) {
            return new UTurn(this.drone, this.flipped);
        }
        return this;
    }
}
