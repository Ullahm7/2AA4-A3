package ca.mcmaster.se2aa4.island.teamXXX;

public class Drone {

    private Integer batteryLevel;
    InterestPoints direction;
    LocationPoint currentLocation;
    Heading currentHeading;
    Heading initialHeading;

    //decision making variables:
    String action;
    String decision;

    //enums of heading directions 
    public enum Heading {
        N, E, S, W
    }

    //methods of turning directions:
    //this turns the drone left depending on currentHeading
    public Heading leftSide(Heading currentHeading) {
        switch (currentHeading) {
            case N:
                return Heading.W;
            case E:
                return Heading.N;
            case S:
                return Heading.E;
            case W:
                return Heading.S;
            default:
                throw new IllegalArgumentException("Invalid heading: " + currentHeading);
        }
    }

    public Heading rightSide(Heading currentHeading) {
        switch (currentHeading) {
            case N:
                return Heading.E;
            case E:
                return Heading.S;
            case S:
                return Heading.W;
            case W:
                return Heading.N;
            default:
                throw new IllegalArgumentException("Invalid heading: " + currentHeading);
        }
    }

    public Heading backSide(Heading currentHeading) {
        switch (currentHeading) {
            case N:
                return Heading.S;
            case E:
                return Heading.W;
            case S:
                return Heading.N;
            case W:
                return Heading.E;
            default:
                throw new IllegalArgumentException("Invalid heading: " + currentHeading);
        }
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
