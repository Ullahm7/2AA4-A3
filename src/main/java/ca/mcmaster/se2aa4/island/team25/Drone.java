package ca.mcmaster.se2aa4.island.team25;

public class Drone {

    private Direction direction;
    private Battery battery;

    public Drone(int battery, char heading) {

        this.direction = Direction.charToDir(heading);
        this.battery = new Battery(battery);
    
    }

    public checkForward() {
        
    }


}