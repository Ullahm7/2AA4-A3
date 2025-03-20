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
