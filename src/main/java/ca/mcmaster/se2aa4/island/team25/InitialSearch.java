package ca.mcmaster.se2aa4.island.team25;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class InitialSearch implements SearchMethodInfo {

    //private final Logger logger = LogManager.getLogger();

    private Drone drone;
    protected int stepNum = -1;
    protected boolean landFound = false;
    protected int distToIsland = 0;
    protected boolean turnToLand = false;

    private SearchFactory searchFactory;
    private SearchInfoFactory searchInfoFactory;

    protected boolean searchDone = false;

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
            //logger.info("*** FINISING INITIAL ***");
            return new StraightLine(this.drone, false);
        } else if (drone.goHome()) {
            return new FindHome(this.drone);  
        } 
        else {
            return this;
        }
    }
}
