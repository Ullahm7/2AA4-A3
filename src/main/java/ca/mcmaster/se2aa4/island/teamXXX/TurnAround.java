package ca.mcmaster.se2aa4.island.teamXXX;
import ca.mcmaster.se2aa4.island.teamXXX.*;

public class TurnAround implements Decisions {

    Boolean reachedEnd = false;

    Search search;
    int counter;

    public TurnAround(Search search) {
        this.search = search;
    }

    @Override
    public Boolean isReached() {
        return reachedEnd;
    }

    @Override
    public String nextDecision(Drone drone, MapRepresenter map) {
        if (counter == 2) {
            counter = 0;
            search.gridSearchDirection = drone.getCurrentHeading();
            search.atEdge = false;
            reachedEnd = true;
        } else {
            return normalTurnAroundGridSearch(search.sideToTurn, drone);
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

    private String normalTurnAroundGridSearch(String sideToTurn, Drone drone) {
        if (sideToTurn.equals("left")) {
            if (counter == 0) {
                counter++;
                return drone.heading(drone.getCurrentHeading().leftSide());
            } else if (counter == 1) {
                counter++;
                return drone.heading(drone.getCurrentHeading().leftSide());
            }
        } else if (sideToTurn.equals("right")) {
            if (counter == 0) {
                counter++;
                return drone.heading(drone.getCurrentHeading().rightSide());
            } else if (counter == 1) {
                counter++;
                return drone.heading(drone.getCurrentHeading().rightSide());
            }
        }
        return null;
    }
    
}
