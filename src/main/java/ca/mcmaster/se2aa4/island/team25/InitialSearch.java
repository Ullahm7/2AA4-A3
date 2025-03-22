package ca.mcmaster.se2aa4.island.team25;

import org.json.JSONObject;

public class InitialSearch implements SearchMethod {

    private Drone drone;
    private int stepNum = 0;
    private boolean landFound = false;

    public InitialSearch(Drone drone) {
        this.drone = drone;
    }

    public JSONObject nextStep() {
        if (!landFound) {

            return this.drone.radarDirection(this.drone.currentDir().turnRight());
        }
        else {
            return this.drone.fly();
        }
                
    }

    public void giveInfo(JSONObject info) {
        //
    }

}
