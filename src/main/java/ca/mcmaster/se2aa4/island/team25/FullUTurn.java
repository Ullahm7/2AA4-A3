package ca.mcmaster.se2aa4.island.team25;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team25.Factories.*;

public class FullUTurn implements SearchMethod {

    private Drone drone;

    private int counter = 0;
    private boolean isNorth;

    private FindHomeFactory findHomeFactory;
    private StraightLineFactory straightLineFactory;

    public FullUTurn(Drone drone) {
        this.drone = drone;
        this.isNorth = (this.drone.currentDir() == Direction.NORTH);
        this.findHomeFactory = new FindHomeFactory();
        this.straightLineFactory = new StraightLineFactory();
    }

    public JSONObject nextStep() {
 
        this.counter++;
        if (this.counter == 1 || this.counter == 3) {
            return this.drone.simpleAction(Action.FLY);
        }

        return this.isNorth
                ? this.drone.turnLeft()
                : this.drone.turnRight();
    }

    @Override
    public SearchMethod searchType() {
        if (drone.goHome()) {
           
            return findHomeFactory.createSearch(this.drone,false);
        }

        if (this.counter == 4) {

            return straightLineFactory.createSearch(this.drone, true);
        }
        return this;
    }
}
