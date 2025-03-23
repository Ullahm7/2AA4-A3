package ca.mcmaster.se2aa4.island.teamXXX;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ca.mcmaster.se2aa4.island.teamXXX.Drone.Heading;

public class Mapping {
    private final Logger logger = LogManager.getLogger();

    Integer topY;
    Integer bottomY;
    Integer leftX;
    Integer rightX;
    Boolean facingGround;
    Heading directionToEcho;
    Boolean spawnedFacingGround = false;

    int distanceToGround = 0;

    Boolean middle;
    Boolean echoed = false;
    Heading generalDirection;
    Integer outOfRangeCounter = 0;

    MapRepresenter map;
    Drone drone;

    public Mapping(Drone drone, MapRepresenter map) {
        this.map = map;
    }

    public void initializeMapDimensions(Heading heading, Integer range) {
        switch (heading) {
            case N:
                topY = range;
                break;
            case E:
                rightX = range;
                break;
            case S:
                bottomY = range;
                break;
            case W:
                leftX = range;
                break;
            default:
                break;
        }
    }
    
    public void initializeRowsAndColumns() {
        if (topY != null && bottomY != null && leftX != null && rightX != null) {
            map.rows = topY + bottomY + 1;
            map.columns = leftX + rightX + 1;
        }
    }
    public void directionToEcho(Heading currentHeading) {
        if (currentHeading == Heading.N || currentHeading == Heading.S) {
            if (leftX > rightX) {
                directionToEcho = Heading.W;
            } else if (rightX > leftX) {
                directionToEcho = Heading.E;
            } else if (leftX == rightX) {
                directionToEcho = Heading.E;
            }
        } else if (currentHeading == Heading.E || currentHeading == Heading.W) {
            if (topY > bottomY) {
                directionToEcho = Heading.N;
            } else if (bottomY > topY) {
                directionToEcho = Heading.S;
            } else if (bottomY == topY) {
                directionToEcho = Heading.N;
            }
        }
    }
}
