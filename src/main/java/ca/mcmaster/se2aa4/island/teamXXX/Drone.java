package ca.mcmaster.se2aa4.island.teamXXX;

public class Drone {

    private Integer batteryLevel;
    InterestPoints direction;
    LocationPoint currentLocation;
    Heading currentHeading;

    public enum Heading {
        N, E, S, W
    }

    public Drone(Integer batteryLevel, String currentHeading, MapRepresenter map) {
        this.batteryLevel = batteryLevel;
        this.currentHeading = Heading.valueOf(currentHeading);
    }

    public void fly(LocationPoint currentLocation){

    }

    public void Heading(){

    }

    public String echo(){
        return null;

    }

    public boolean stop(Integer batteryLevel){

        return false;

    }
    
}
