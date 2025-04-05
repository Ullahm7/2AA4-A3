package ca.mcmaster.se2aa4.island.team25.Searches;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team25.Action;
import ca.mcmaster.se2aa4.island.team25.Direction;
import ca.mcmaster.se2aa4.island.team25.Drone;
import ca.mcmaster.se2aa4.island.team25.SearchMethod;

public class FindHome implements SearchMethod {

    private Drone drone;

    public FindHome(Drone drone) {
        this.drone = drone;
    }

    public JSONObject nextStep() {
    
        if (drone.mustStop()) {
            return this.drone.simpleAction(Action.STOP);
        } else {
            if (drone.northToHome() > 1) {
                if (drone.currentDir() != Direction.NORTH) {
                    if (drone.currentDir() == Direction.EAST) {
                        return this.drone.turnLeft();
                    }
                    return this.drone.turnRight();
                }
                return this.drone.simpleAction(Action.FLY);
            } else if (this.drone.westToHome() > 0) {
                if (this.drone.currentDir() != Direction.WEST) {
                    return this.drone.turnLeft();
                }
                return this.drone.simpleAction(Action.FLY);
            }
            return this.drone.simpleAction(Action.STOP);
        }
    }

    public SearchMethod searchType() {
        return this;
    }
}
