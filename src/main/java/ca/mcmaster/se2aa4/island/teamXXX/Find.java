package ca.mcmaster.se2aa4.island.teamXXX;

import eu.ace_design.island.game.actions.Scan;

public class Find implements Decisions {
    int counter = 0;
    int flyCounter = 0;
    boolean reachedEnd = false;
    boolean foundDimension = false;
    Mapping mapping;

    public Find(Mapping mapping){

        this.mapping = mapping;

    }

    @Override
    public Boolean isReached() {
        return reachedEnd;
    }

    @Override
    public String nextDecision(Storage responseStorage, Drone drone, MapRepresenter map) {

        if (!foundDimension) {
            if (counter == 0) {
                counter++;
                flyCounter++;
                return drone.fly();
            } else {
                counter = 0;
                return drone.echo(drone.getCurrentHeading());
            }
        } else {
            reachedEnd = true;
            return drone.scan();
        }
    }

    @Override
    public Decisions getStage() {
        return new Scan(new GridSearch(mapping.drone, mapping.map));
    }

    @Override
    public Boolean isFinal() {
        return false;
    }

    @Override
    public void processResponse(Storage responseStorage, Drone drone, MapRepresenter map) {
        if (drone.getAction().equals("echo")) {
            if (responseStorage.getFound().equals("OUT_OF_RANGE")) {
                mapping.initializeMapDimensions(drone.getCurrentHeading(), responseStorage.getRange());
                mapping.initializeMapDimensions(drone.getCurrentHeading().backSide(), flyCounter);
                mapping.initializeRowsAndColumns();
                map.initializeMap();
                drone.initializeCurrentLocation(mapping.leftX, mapping.topY,mapping.spawnedFacingGround);
                foundDimension = true;
            }
        }
    }
    
}
