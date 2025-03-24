package ca.mcmaster.se2aa4.island.teamXXX;

import ca.mcmaster.se2aa4.island.teamXXX.*;

public class Check implements ProcessDecisions { 

    Boolean reachedEnd = false;
    Boolean isFinal = false;

    Search search;

    Decisions nextDecisions;

    public Check(Search search) {

        this.search = search;

    }

    @Override
    public Boolean isReached() {
        return reachedEnd;
    }

    @Override
    public String nextDecision(Drone drone, MapRepresenter map) {
        return drone.echo(drone.getCurrentHeading());
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
        if (responseStorage.getFound().equals("OUT_OF_RANGE") && !search.translated) {
            map.setAsScanned(drone, responseStorage.getRange(), drone.getCurrentHeading());
            map.setAsScanned(drone, responseStorage.getRange(), drone.getCurrentHeading().backSide());
            reachedEnd = true;
            search.outOfRangeCounter++;
            if (search.middle && search.outOfRangeCounter == 3) {
                isFinal = true;
            }else if (!search.middle && search.outOfRangeCounter == 2) {
                isFinal = true;
            }
            else{
                nextDecisions = new Translate(search);
            }
        }else if (responseStorage.getFound().equals("OUT_OF_RANGE") && search.translated) {
            map.setAsScanned(drone, responseStorage.getRange(), drone.getCurrentHeading());
            map.setAsScanned(drone, responseStorage.getRange(), drone.getCurrentHeading().backSide());
            reachedEnd = true;
            search.distanceToFly = responseStorage.getRange() - 5;
            nextDecisions = new NoScan(search);
        }else if (responseStorage.getFound().equals("GROUND")) {
            map.setAsScanned(drone, responseStorage.getRange(), drone.getCurrentHeading());
            map.setAsScanned(drone, responseStorage.getRange(), drone.getCurrentHeading().backSide());
            reachedEnd = true;
            search.translated = false;
            search.distanceToFly = responseStorage.getRange();
            if (search.distanceToFly == 0) {
                nextDecisions = new Scan(search);
            } else {
                nextDecisions = new NoScan(search);
            }
        }
    }
    
}
