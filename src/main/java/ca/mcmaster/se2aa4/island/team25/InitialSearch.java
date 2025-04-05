package ca.mcmaster.se2aa4.island.team25;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team25.Factories.*;

public class InitialSearch implements SearchMethodInfo {

    private Drone drone;
    protected int stepNum = -1;
    protected boolean landFound = false;
    protected int distToIsland = 0;
    protected boolean turnToLand = false;

    private FindHomeFactory findHomeFactory;
    private StraightLineFactory straightLineFactory;


    protected boolean searchDone = false;

    public InitialSearch(Drone drone) {
        this.drone = drone;
        this.findHomeFactory = new FindHomeFactory();
        this.straightLineFactory = new StraightLineFactory();
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
        } else {
            this.searchDone = true;
            return this.drone.simpleAction(Action.SCAN);
        }
    }

    public void giveInfo(JSONObject info) {
        if (info.has("found")) {
            if ("GROUND".equals(info.getString("found"))) {
                this.landFound = true;
                this.distToIsland = info.getInt("range") + stepNum - 2;
            }
        }
    }

    public SearchMethod searchType() {
        if (this.searchDone) {
            return straightLineFactory.createSearch(this.drone, false);
        } else if (drone.goHome()) {
            return findHomeFactory.createSearch(this.drone, false);  
        } 
        else {
            return this;
        }
    }
}
