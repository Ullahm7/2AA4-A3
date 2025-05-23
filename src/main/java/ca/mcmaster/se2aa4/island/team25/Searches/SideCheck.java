package ca.mcmaster.se2aa4.island.team25.Searches;
import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team25.Action;
import ca.mcmaster.se2aa4.island.team25.Direction;
import ca.mcmaster.se2aa4.island.team25.Drone;
import ca.mcmaster.se2aa4.island.team25.SearchMethod;
import ca.mcmaster.se2aa4.island.team25.SearchMethodInfo;
import ca.mcmaster.se2aa4.island.team25.Factories.*;

public class SideCheck implements SearchMethodInfo {

    private Drone drone;
    private Direction currentDir;
    private UTurnFactory uTurnFactory;
    private FindHomeFactory findHomeFactory;

    private boolean flipped;
    private boolean sideCheck = false;
    private boolean emptySide = false;
    private boolean frontCheck = true;
    private boolean fullTurn = false;


    public SideCheck(Drone drone, boolean flipped) {
        this.drone = drone;
        this.flipped = flipped;
        this.currentDir = this.drone.currentDir();
        this.uTurnFactory = new UTurnFactory();
        this.findHomeFactory = new FindHomeFactory();
    }

    public JSONObject nextStep() {
        if (this.flipped) {
            this.currentDir = this.drone.currentDir().turnLeft().turnLeft();
        }
        
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

    public SearchMethod searchType() {
        if (drone.goHome()) {

            
            return findHomeFactory.createSearch(this.drone, this.flipped);

        }
        if (this.fullTurn || this.emptySide) {

            
            return uTurnFactory.createSearch(this.drone, this.flipped);

        }
        return this;
    }
}
