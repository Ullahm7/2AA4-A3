package ca.mcmaster.se2aa4.island.teamXXX;
import ca.mcmaster.se2aa4.island.teamXXX.*;

public class Turn implements Decisions {

    boolean reachedEnd = false;
    int counter = 0;
    String sideToTurn;

    Mapping mapping;

    public Turn(Mapping mapping) {
        this.mapping = mapping;
    }

    @Override
    public Boolean isReached() {
        return reachedEnd;
    }

    @Override
    public String nextDecision(Drone drone, MapRepresenter map) {
        if (counter == 0) {
            if (mapping.directionToEcho == drone.getCurrentHeading().leftSide()) {
                sideToTurn = "left";
            } else {
                sideToTurn = "right";

            }
        }
        if (counter == 7) {
            reachedEnd = true;
            mapping.distanceToGround -= 2;
            return null;
        }
        return turn(sideToTurn, drone);
    }

    @Override
    public Decisions getStage() {
        return new Fly(mapping);
    }

    @Override
    public Boolean isFinal() {
        return false;
    }

    private String turn(String sideToTurn, Drone drone) {
        if (sideToTurn.equals("left")) {
            // Only needs one spot above it turn
            if (counter == 0) {
                counter++;
                return drone.heading(drone.getCurrentHeading().leftSide());
            } else if (counter == 1) {
                counter++;
                return drone.fly();
            } else if (counter == 2) {
                counter++;
                return drone.heading(drone.getCurrentHeading().leftSide());
            } else if (counter == 3) {
                counter++;
                return drone.fly();
            } else if (counter == 4) {
                counter++;
                return drone.heading(drone.getCurrentHeading().leftSide());
            } else if (counter == 5) {
                counter++;
                return drone.heading(drone.getCurrentHeading().leftSide());
            } else if (counter == 6) {
                counter++;
                return drone.heading(drone.getCurrentHeading().leftSide());
            }
        } else if (sideToTurn.equals("right")) {
            // needs one spot above it to turn
            if (counter == 0) {
                counter++;
                return drone.heading(drone.getCurrentHeading().rightSide());
            } else if (counter == 1) {
                counter++;
                return drone.fly();
            } else if (counter == 2) {
                counter++;
                return drone.heading(drone.getCurrentHeading().rightSide());
            } else if (counter == 3) {
                counter++;
                return drone.fly();
            } else if (counter == 4) {
                counter++;
                return drone.heading(drone.getCurrentHeading().rightSide());
            } else if (counter == 5) {
                counter++;
                return drone.heading(drone.getCurrentHeading().rightSide());
            } else if (counter == 6) {
                counter++;
                return drone.heading(drone.getCurrentHeading().rightSide());
            }
        }
        return null;
    }
    
    
}
