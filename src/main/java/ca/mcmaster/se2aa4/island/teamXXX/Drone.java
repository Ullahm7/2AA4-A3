package ca.mcmaster.se2aa4.island.teamXXX;

import java.lang.reflect.AccessFlag.Location;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ca.mcmaster.se2aa4.island.teamXXX.Heading;

public class Drone {

    //variable declaration
    private final Logger logger = LogManager.getLogger(); 
    private Integer batteryLevel;
    Heading direction;
    LocationPoint currentLocation;
    Heading currentHeading;
    Heading initialHeading;
    public MapRepresenter map;
    Boolean spawnedFacingGround = false;
    //decision making variables:
    String action;
    String decision;

    //drone contructor, will implement the mapping soon
    public Drone(Integer batteryLevel, String initialHeading, MapRepresenter map) {
        this.batteryLevel = batteryLevel;
        this.currentHeading = Heading.valueOf(initialHeading);
        this.initialHeading = Heading.valueOf(initialHeading);
        this.currentLocation = new Points(100, 100); 
        this.map = map;
    }

    //updates current location by one coord, the fly method continues to go in that direction, flys forward
    public String fly() {
        try {
            switch (currentHeading) {
                case N:
                    currentLocation = map.map.get(currentLocation.getRow() - 1).get(currentLocation.getColumn());
                    break;
                case E:
                    currentLocation = map.map.get(currentLocation.getRow()).get(currentLocation.getColumn() + 1);
                    break;
                case S:
                    currentLocation = map.map.get(currentLocation.getRow() + 1).get(currentLocation.getColumn());
                    break;
                case W:
                    currentLocation = map.map.get(currentLocation.getRow()).get(currentLocation.getColumn() - 1);
                    break;
                default:
                    break;
            }
        } catch (IndexOutOfBoundsException e) {
            return decisionTaken("stop");
        }
        return decisionTaken("fly");
    }

    //this updates currentHeading and allows for the next heading to be a specific turn 
    public void initializeCurrentLocation(Integer leftX, Integer topY, Boolean spawnedFacingGround) {
        int x;
        int y;
        this.spawnedFacingGround = spawnedFacingGround;

        // we didnt change heading and so leftX and topY are the same as the current
        // location
        if (spawnedFacingGround) {
            x = topY;
            y = leftX;
        }
        // since we changed heading, leftX and topY are off by a bit, the 100 we
        // intialized currentLocation at (100, 100)
        else {
            x = topY + currentLocation.getRow() - 100;
            y = leftX + currentLocation.getColumn() - 100;
        }

        currentLocation = map.map.get(x).get(y);
    }

    // this method also updates current location based on current heading and next
    // heading
    public String heading(Heading heading) {
        if (heading == currentHeading || heading == currentHeading.backSide()) {
            throw new IllegalArgumentException("Invalid heading");
        }
        try {
            if (heading == currentHeading.leftSide()) {
                switch (currentHeading) {
                    case N:
                        currentLocation = map.map.get(currentLocation.getRow() - 1)
                                .get(currentLocation.getColumn() - 1);
                        break;
                    case E:
                        currentLocation = map.map.get(currentLocation.getRow() - 1)
                                .get(currentLocation.getColumn() + 1);
                        break;
                    case S:
                        currentLocation = map.map.get(currentLocation.getRow() + 1)
                                .get(currentLocation.getColumn() + 1);
                        break;
                    case W:
                        currentLocation = map.map.get(currentLocation.getRow() + 1)
                                .get(currentLocation.getColumn() - 1);
                        break;
                    default:
                        return null;
                }
            }
    
            if (heading == currentHeading.rightSide()) {
                switch (currentHeading) {
                    case N:
                        currentLocation = map.map.get(currentLocation.getRow() - 1)
                                .get(currentLocation.getColumn() + 1);
                        break;
                    case E:
                        currentLocation = map.map.get(currentLocation.getRow() + 1)
                                .get(currentLocation.getColumn() + 1);
                        break;
                    case S:
                        currentLocation = map.map.get(currentLocation.getRow() + 1)
                                .get(currentLocation.getColumn() - 1);
                        break;
                    case W:
                        currentLocation = map.map.get(currentLocation.getRow() - 1)
                                .get(currentLocation.getColumn() - 1);
                        break;
                    default:
                        return null;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            return decisionTaken("stop");
        }
        return decisionTaken("heading", heading.toString());
    }

    public Heading getDirection() {
        return direction;
    }

    public String echo(Heading heading) {
        return decisionTaken("echo", heading.toString());
    }

    public String scan() {
        return decisionTaken("scan");
    }

    public String stop() {
        return decisionTaken("stop");
    }

    public Integer getBatteryLevel() {
        return batteryLevel;
    }

    public String getAction() {
        return action;
    }

    public LocationPoint getCurrentLocation() {
        return currentLocation;
    }

    public Heading getCurrentHeading() {
        return currentHeading;
    }
    public void setCurrentHeading(Heading currentHeading) {
        this.currentHeading = currentHeading;
    }

    public void setCurrentLocation(LocationPoint currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Boolean getSpawnedFacingGround() {
        return spawnedFacingGround;
    }


    public void updateBatteryLevel(Integer cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative");
        }
        batteryLevel -= cost;
    }

    String decisionTaken(String command) {

        // ensures the commands are valid
        if (!command.equals("fly") && !command.equals("scan") && !command.equals("stop")) {
            throw new IllegalArgumentException("Invalid command");
        }
        action = command;
        String nextDecision = "{\"action\": \"" + command + "\"}";
        return nextDecision;
    }

    String decisionTaken(String command, String direction) {

        // need to make sure that the commands are valid
        if (!command.equals("echo") && !command.equals("heading")) {
            throw new IllegalArgumentException("Invalid command");
        }
        // ensures the direction is valid
        if (!direction.equals("N") && !direction.equals("E") && !direction.equals("S") && !direction.equals("W")) {
            throw new IllegalArgumentException("Invalid direction");
        }

        // if the command is heading, then the currentDirection is the new heading
        if (command.equals("heading")) {
            this.currentHeading = Heading.valueOf(direction);
        }

        // store the parameters of the next decision
        action = command;
        this.direction = Heading.valueOf(direction);

        String nextDecision = "{\"action\": \"" + command + "\", \"parameters\": { \"direction\": \"" + direction
                + "\"}}";
        return nextDecision;
    }

    

}
