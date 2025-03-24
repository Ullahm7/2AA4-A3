package ca.mcmaster.se2aa4.island.team25;

import org.json.JSONObject;

public class Drone {

    private Direction direction;
    private Battery battery;
    private Coordinate cords;

    private int turnCounter = -1;
    private int perfectTurn = -1;

    public Drone(int battery, char heading) {

        this.direction = Direction.charToDir(heading);
        this.battery = new Battery(battery);

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
        this.direction = this.direction.turnLeft();
        return createDirectionAction("heading", this.direction);
    }

    public JSONObject turnRight() {
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
