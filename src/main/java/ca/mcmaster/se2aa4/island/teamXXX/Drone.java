package ca.mcmaster.se2aa4.island.teamXXX;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Drone {

    //variable declaration
    private final Logger logger = LogManager.getLogger(); 
    private Integer batteryLevel;
    String direction;
    LocationPoint currentLocation;
    Heading currentHeading;
    Heading initialHeading;

    //decision making variables:
    String action;
    String decision;

    //drone contructor, will implement the mapping soon
    public Drone(Integer batteryLevel, String initialHeading, MapRepresenter map) {
        this.batteryLevel = batteryLevel;
        this.currentHeading = Heading.valueOf(initialHeading);
        this.initialHeading = Heading.valueOf(initialHeading);
        this.currentLocation = new LocationPoint(0, 0); 
    }

    //enums of heading directions 
    public enum Heading {
        N, E, S, W;

        //methods to find direction on left, right and back sides
        //this finds direction on left side
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
        //find direction on right side
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
        //this finds direction at the back
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
    //this method turns the drone left leftSide method of the Heading enum and then calls decisionTaken to prepare a JSON string 
    public String turnLeft() {
        currentHeading = currentHeading.leftSide(currentHeading);
        return decisionTaken("heading", currentHeading.toString());
    }
    //similarly this turns right
    public String turnRight() {
        currentHeading = currentHeading.rightSide(currentHeading);
        return decisionTaken("heading", currentHeading.toString());
    }

    //updates current location by one coord, the fly method continues to go in that direction, flys forward
    public String fly(){
        switch (currentHeading){
            case N:
                currentLocation = new LocationPoint(currentLocation.getX(), currentLocation.getY() + 1); //moves up
                break;
            case E:
                currentLocation = new LocationPoint(currentLocation.getX() + 1, currentLocation.getY()); //moves right
                break;
            case S:
                currentLocation = new LocationPoint(currentLocation.getX(), currentLocation.getY() - 1); //moves down
                break;
            case W:
                currentLocation = new LocationPoint(currentLocation.getX() - 1, currentLocation.getY()); //moves left
                break;
            default:
                break;
        }

        return decisionTaken("fly");
    }

    //this updates currentHeading and allows for the next heading to be a specific turn 
    public String heading(Heading heading){ 
        if (heading == currentHeading.leftSide(currentHeading)){ //checks if left turn is valid, e.g if it is going E, can't make W turns 
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
            //may need to add more instances
        }
        return decisionTaken("heading", heading.toString()); //returns a valid heading and changes currentLocation
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

    //takes in fly, scan, and stop command, they don't require a direction (will change)
    private String decisionTaken(String command){

        if (!command.equals("fly") && !command.equals("scan") && !command.equals("stop")){ 
            logger.info("Invalid command");
            System.exit(0);
        }
        action = command;
        String nextDecision = "{\"action\": \""+ command +"\"}";
        return nextDecision;
    }

    //takes in only echo and heading as they will require a direction (will change)
    private String decisionTaken(String command, String direction){

        if (!command.equals("echo") && !command.equals("heading")){
            logger.info("Invalid command");
            System.exit(0);
        }

        if (!direction.equals("N") && !direction.equals("E") && !direction.equals("S") && !direction.equals("W")){
            logger.info(direction);
            logger.info("Invalid direction");
            System.exit(0);
        }

        //if the command is heading, then the currentDirection is the new heading
        if (command.equals("heading")){
            this.currentHeading = Heading.valueOf(direction); //
        }

        //store the parameters of the next decision
        action = command;
        this.direction = direction; 

        String nextDecision = "{\"action\": \""+ command +"\", \"parameters\": { \"direction\": \"" + direction +"\"}}";
        return nextDecision;
    }

}
