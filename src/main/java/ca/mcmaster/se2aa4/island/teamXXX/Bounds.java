package ca.mcmaster.se2aa4.island.teamXXX;

import ca.mcmaster.se2aa4.island.teamXXX.*;
import java.util.List;
import java.util.ArrayList;

public class Bounds implements ProcessDecisions{

    Boolean reachedEnd = false;
    Boolean isFinal = false;

    // scan first then fly
    Boolean flyCheck = false;

    Search search;

    Decisions nextDecisions;

    public Bounds(Search search) {
        this.search = search;
    }

    @Override
    public Boolean isReached() {
        return reachedEnd;
    }

    @Override
    public String nextDecision(Drone drone, MapRepresenter map) {
        if (!flyCheck) {
            flyCheck = true;
            return drone.echo(search.generalDirection);
        } else if (flyCheck) {
            flyCheck = false;
            return drone.fly();
        }
        return null;
    }

    @Override
    public Decisions getStage() {
        return nextDecisions;
    }

    @Override
    public Boolean isFinal() {
        return isFinal;
    }

    @Override
    public void processResponse(Storage responseStorage, Drone drone, MapRepresenter map) {
        if (drone.getAction().equals("echo")) {
            if (responseStorage.getFound().equals("OUT_OF_RANGE")
                    || (responseStorage.getFound().equals("GROUND") && responseStorage.getRange() > 3)) {
                nextDecisions = new TurnAround(search);
                reachedEnd = true;
            }
        }
    }
    
}
