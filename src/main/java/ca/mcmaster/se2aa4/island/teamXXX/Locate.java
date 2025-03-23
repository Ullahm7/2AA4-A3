package ca.mcmaster.se2aa4.island.teamXXX;

public class Locate implements Decisions {

    Boolean flyCheck = true;
    Boolean foundLand = false;
    Boolean reachEnd = false;
    Boolean temperaryState = true;
    Mapping mapping;

    int counter = 0;
    boolean reachedEnd = false;

    public Locate(Mapping mapping) {
        this.mapping = mapping;
    }

    @Override
    public Boolean isReached() {
        return reachedEnd;
    }

    @Override
    public String nextDecision(Storage responseStorage, Drone drone, MapRepresenter map) {
        if (counter == 0) {
            counter++;
            return drone.fly();
        } else if (counter == 1) {
            counter = 0;
            return drone.echo(mapping.directionToEcho);
        } else {
            return null;
        }
    }

    @Override
    public Decisions getStage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStage'");
    }

    @Override
    public Boolean isFinal() {
        return false;
    }

    @Override
    public void processResponse(Storage responseStorage, Drone drone, MapRepresenter map) {
        if (responseStorage.getFound().equals("GROUND")) {
            reachedEnd = true;
            mapping.distanceToGround = responseStorage.getRange() + 1;
        }
        if (responseStorage.getFound().equals("OUT_OF_RANGE")) {
            map.setAsScanned(drone, responseStorage.getRange(), mapping.directionToEcho);
        }
    }
    
}
