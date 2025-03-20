package ca.mcmaster.se2aa4.island.teamXXX;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Drone {

    private final Logger logger = LogManager.getLogger(); 
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
        N, E, S, W;

        // methods to quickly find direction on left, right and back sides
        public Heading leftSide(Heading currentHeading) {
            switch (currentHeading) {
                case N:
                    return W;
                case E:
                    return N;
                case S:
                    return E;
                case W:
                    return S;
                default:
                    throw new IllegalArgumentException("Invalid heading: " + currentHeading);
            }
        }
    
        public Heading rightSide(Heading currentHeading) {
            switch (currentHeading) {
                case N:
                    return E;
                case E:
                    return S;
                case S:
                    return W;
                case W:
                    return N;
                default:
                    throw new IllegalArgumentException("Invalid heading: " + currentHeading);
            }
        }
    
        public Heading backSide(Heading currentHeading) {
            switch (currentHeading) {
                case N:
                    return S;
                case E:
                    return W;
                case S:
                    return N;
                case W:
                    return E;
                default:
                    throw new IllegalArgumentException("Invalid heading: " + currentHeading);
            }
        }
    }


    public Drone(Integer batteryLevel, String initialHeading, MapRepresenter map) {
        this.batteryLevel = batteryLevel;
        this.currentHeading = Heading.valueOf(initialHeading);
        this.initialHeading = Heading.valueOf(initialHeading);
        this.currentLocation = new LocationPoint(0, 0);
        
    }

    public String turnLeft() {
        currentHeading = currentHeading.leftSide(currentHeading);
        return decisionTaken("heading", currentHeading.toString());
    }
    
    public String turnRight() {
        currentHeading = currentHeading.rightSide(currentHeading);
        return decisionTaken("heading", currentHeading.toString());
    }

    // this method also updates the current location of the drone
    public String fly(){
        switch (currentHeading){
            case N:
                currentLocation = new LocationPoint(currentLocation.getX(), currentLocation.getY() + 1);
                break;
            case E:
                currentLocation = new LocationPoint(currentLocation.getX() + 1, currentLocation.getY());
                break;
            case S:
                currentLocation = new LocationPoint(currentLocation.getX(), currentLocation.getX() - 1);
                break;
            case W:
                currentLocation = new LocationPoint(currentLocation.getX() - 1, currentLocation.getY());
                break;
            default:
                break;
        }

        return decisionTaken("fly");
    }

    // this method also updates current location based on current heading and next heading
    public String heading(Heading heading){
        if (heading == currentHeading.leftSide(currentHeading)){
            switch (currentHeading){
                case N:
                    currentLocation = new LocationPoint(currentLocation.getX() - 1, currentLocation.getY() + 1);
                    break;
                case E:
                    currentLocation = new LocationPoint(currentLocation.getX() + 1, currentLocation.getY() + 1);
                    break;
                case S:
                    currentLocation = new LocationPoint(currentLocation.getX() + 1, currentLocation.getY() - 1);
                    break;
                case W:
                    currentLocation = new LocationPoint(currentLocation.getX() - 1, currentLocation.getY() - 1);
                    break;
                default:
                    return null;
            }
        }

        if (heading == currentHeading.rightSide(currentHeading)){
            switch (currentHeading){
                case N:
                    currentLocation = new LocationPoint(currentLocation.getX() + 1, currentLocation.getY() + 1);
                    break;
                case E:
                    currentLocation = new LocationPoint(currentLocation.getX() + 1, currentLocation.getY() - 1);
                    break;
                case S:
                    currentLocation = new LocationPoint(currentLocation.getX() - 1, currentLocation.getY() - 1);
                    break;
                case W:
                    currentLocation = new LocationPoint(currentLocation.getX() - 1, currentLocation.getY() + 1);
                    break;
                default:
                    return null;
            }
        }
        return decisionTaken("heading", heading.toString());
    }

    public String echo(Heading heading){
        return decisionTaken("echo", heading.toString());
    }

    public String scan(){
        return decisionTaken("scan");
    }

    public String stop(){
        return decisionTaken("stop");
    }

    private String decisionTaken(String command){
        return "null";
    }
    private String decisionTaken(String command, String direction){
        return "null";
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
