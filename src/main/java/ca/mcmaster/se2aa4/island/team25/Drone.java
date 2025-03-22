package ca.mcmaster.se2aa4.island.team25;

import org.json.JSONObject;

public class Drone {

    private Direction direction;
    private Battery battery;

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
        action.put("action", "echo");
        extra.put("direction", this.direction.turnLeft());
        action.put("parameters", extra);
        
        return action;
    }

    public JSONObject fly() {
        JSONObject action = new JSONObject();
        action.put("action", "fly");

        return action;
    }

    public Direction currentDir() {
        return this.direction;
    }

    public void batteryLost(int cost) {
        battery.lowerBattery(cost);
    } 

}