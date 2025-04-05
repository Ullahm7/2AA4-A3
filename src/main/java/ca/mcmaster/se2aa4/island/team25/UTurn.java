package ca.mcmaster.se2aa4.island.team25;

import org.json.JSONObject;
import ca.mcmaster.se2aa4.island.team25.Factories.*;

public class UTurn implements SearchMethod {

    private Drone drone;
    
    private int turnCount = -1;
    private boolean isNorth;
    private boolean flipped;

    private FindHomeFactory findHomeFactory;
    private StraightLineFactory straightLineFactory;

    public UTurn(Drone drone, boolean flipped) {
        this.drone = drone;
        this.flipped = flipped;
        this.isNorth = (this.drone.currentDir() == Direction.NORTH);
        if (flipped) {
            this.isNorth = !this.isNorth;
        }
        this.findHomeFactory = new FindHomeFactory();
        this.straightLineFactory = new StraightLineFactory();
    }

    public JSONObject nextStep() {

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

    public SearchMethod searchType() {
        if (drone.goHome()) {
            
            return findHomeFactory.createSearch(this.drone, this.flipped);

        }
        if (turnCount < 2) {
            return this;
        }
        
        return straightLineFactory.createSearch(this.drone, this.flipped);
    }
}
