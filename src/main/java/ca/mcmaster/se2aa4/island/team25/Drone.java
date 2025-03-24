package ca.mcmaster.se2aa4.island.team25;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class Drone {

    private final Logger logger = LogManager.getLogger();

    private Direction direction;
    private Battery battery;

    private int turnCounter = -1;
    private int perfectTurn = -1;

    private Coordinate currentCord;

    public Drone(int battery, char heading) {
        this.currentCord = new Coordinate(0,0);
        this.direction = Direction.charToDir(heading);
        this.battery = new Battery(battery);

    }

    public boolean goHome() {
        int currentBatt = this.battery.currentBattery();
        int batteryToHome = ((this.currentCord.getX() + this.currentCord.getY())*4);
        return (currentBatt < batteryToHome); 
    }

    public boolean mustStop() {
        return (battery.currentBattery() < 22);
    }

    public int northToHome() {
        return currentCord.getY();
    }

    public int westToHome() {
        return currentCord.getX();
    }

    public JSONObject createDirectionAction(String actionType, Direction dir) {
        JSONObject action = new JSONObject();
        JSONObject parameters = new JSONObject();

        action.put("action", actionType);
        parameters.put("direction", dir.getIcon());
        action.put("parameters", parameters);

        return action;
    }

    public JSONObject radarDirection(Direction dir) {
        return createDirectionAction("echo", dir);
    }

    public JSONObject turnLeft() {
        this.currentCord.changeCord(direction);
        this.currentCord.changeCord(direction.turnLeft());
        this.direction = this.direction.turnLeft();
        return createDirectionAction("heading", this.direction);
    }

    public JSONObject turnRight() {
        this.currentCord.changeCord(direction);
        this.currentCord.changeCord(direction.turnRight());
        this.direction = this.direction.turnRight();
        return createDirectionAction("heading", this.direction);
    }

    public JSONObject uTurn() {
        this.turnCounter++;
        if ((this.turnCounter / 2) % 2 == 0) {
            return this.turnLeft();
        } else {
            return this.turnRight();
        }
    }

    //Happens once the Drone has reached the end of the every-other-row system
    public JSONObject perfectUTurn(int step) {
        switch (step) {
            case(1): return this.turnRight();
            case(2): return this.turnRight();
            case(3): return this.turnRight();
            case(4): return this.simpleAction(Action.FLY);
            case(5): 
            this.turnCounter+=2;
            return this.turnRight();
            default: return this.simpleAction(Action.STOP);
        }

    }

    public JSONObject simpleAction(Action type) {
        if (type == Action.FLY) {
            this.currentCord.changeCord(this.direction);
        }
        JSONObject action = new JSONObject();
        action.put("action", type.term);
        return action;
    }

    public Direction currentDir() {
        return this.direction;
    }

    public void batteryLost(int cost) {
        battery.lowerBattery(cost);
    }

}
