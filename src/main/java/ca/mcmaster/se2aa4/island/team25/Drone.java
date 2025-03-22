package ca.mcmaster.se2aa4.island.team25;

import org.json.JSONObject;

public class Drone {

    private Direction direction;
    private Battery battery;
    private Coordinate cords;

    public Drone(int battery, char heading) {

        this.direction = Direction.charToDir(heading);
        this.battery = new Battery(battery);

    }

    public JSONObject radarDirection(Direction dir) {
        JSONObject action = new JSONObject();
        JSONObject extra = new JSONObject();
        action.put("action", "echo");
        extra.put("direction", dir.getIcon());
        action.put("parameters", extra);

        return action;
    }

    public JSONObject turnLeft() {
        JSONObject action = new JSONObject();
        JSONObject extra = new JSONObject();
        action.put("action", "heading");
        extra.put("direction", this.direction.turnLeft().getIcon());
        action.put("parameters", extra);

        this.direction = this.direction.turnLeft();
        return action;
    }

    public JSONObject turnRight() {
        JSONObject action = new JSONObject();
        JSONObject extra = new JSONObject();
        action.put("action", "heading");
        extra.put("direction", this.direction.turnRight().getIcon());
        action.put("parameters", extra);

        this.direction = this.direction.turnRight();
        return action;
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
