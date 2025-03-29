package ca.mcmaster.se2aa4.island.team25;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class FullUTurn implements SearchMethod {

    //private final Logger logger = LogManager.getLogger();
    private Drone drone;

    private int counter = 0;
    private boolean isNorth;

    public FullUTurn(Drone drone) {
        this.drone = drone;
        this.isNorth = (this.drone.currentDir() == Direction.NORTH);
    }

    @Override
    public JSONObject nextStep() {
        //logger.info("*** STOPPING ***" + (this.counter + 1));

        this.counter++;
        if (this.counter == 1 || this.counter == 3) {
            return this.drone.simpleAction(Action.FLY);
        }

        return this.isNorth
                ? this.drone.turnLeft()
                : this.drone.turnRight();
    }

    @Override
    public void giveInfo(JSONObject info) {
    }

    @Override
    public SearchMethod searchType() {
        if (drone.goHome()) {
            return new FindHome(this.drone);
        }

        if (this.counter == 4) {
            return new StraightLine(this.drone, true);
        }
        return this;
    }
}
