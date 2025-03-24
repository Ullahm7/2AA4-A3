package ca.mcmaster.se2aa4.island.team25;

import ca.mcmaster.se2aa4.island.team25.*;

public class Find implements ProcessDecisions {
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
    public Decisions getStage() {
        return new Scan(new Search(mapping.drone, mapping.map));
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
                drone.initializeCurrentLocation(mapping.leftColumns, mapping.topRows,mapping.spawnedFacingGround);
                foundDimension = true;
            }
        }
    }

    @Override
    public String nextDecision(Drone drone, MapRepresenter map) {
        // Fly to opposite side of the island and then find the missing dimension, end
        // by scanning the ground
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
    
}
