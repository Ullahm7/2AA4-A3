package ca.mcmaster.se2aa4.island.teamXXX;
import ca.mcmaster.se2aa4.island.teamXXX.*;

public class Translate implements Decisions {

    Boolean reachedEnd = false;
    Integer turnCounter = 0;

    Search search;
    String sideToTranslate;

    public Translate(Search search) {
        this.search = search;
    }

    @Override
    public Boolean isReached() {
        return reachedEnd;
    }

    @Override
    public String nextDecision(Drone drone, MapRepresenter map) {
        if (turnCounter == 5) {
            turnCounter = 0;
            search.atEdge = false;
            search.generalDirection = search.generalDirection.backSide();
            reachedEnd = true;
            search.translated = true;
        }
        else{
            if (search.gridSearchDirection == search.generalDirection.leftSide()) {
                sideToTranslate = "left";
            } else if (search.gridSearchDirection == search.generalDirection.rightSide()) {
                sideToTranslate = "right";
            }
            return translateOver(sideToTranslate, drone);
        }
        return null;
    }

    private String translateOver(String sideToTranslate, Drone drone) {

        if (sideToTranslate.equals("left")) {
            if (turnCounter == 0) {
                turnCounter++;
                return drone.heading(drone.getCurrentHeading().leftSide());
            } else if (turnCounter == 1) {
                turnCounter++;
                return drone.fly();
            } else if (turnCounter == 2) {
                turnCounter++;
                return drone.heading(drone.getCurrentHeading().leftSide());
            } else if (turnCounter == 3) {
                turnCounter++;
                return drone.heading(drone.getCurrentHeading().leftSide());
            } else if (turnCounter == 4) {
                turnCounter++;
                return drone.heading(drone.getCurrentHeading().leftSide());
            }
        } else if (sideToTranslate.equals("right")) {
            if (turnCounter == 0) {
                turnCounter++;
                return drone.heading(drone.getCurrentHeading().rightSide());
            } else if (turnCounter == 1) {
                turnCounter++;
                return drone.fly();
            } else if (turnCounter == 2) {
                turnCounter++;
                return drone.heading(drone.getCurrentHeading().rightSide());
            } else if (turnCounter == 3) {
                turnCounter++;
                return drone.heading(drone.getCurrentHeading().rightSide());
            } else if (turnCounter == 4) {
                turnCounter++;
                return drone.heading(drone.getCurrentHeading().rightSide());
            }
        }
        return null;

    }
    
    @Override
    public Decisions getStage() {
        return new Check(search);
    }

    @Override
    public Boolean isFinal() {
        return false;
    }
    
}
