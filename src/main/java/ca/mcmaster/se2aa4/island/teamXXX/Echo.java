package ca.mcmaster.se2aa4.island.teamXXX;

public class Echo implements ProcessDecisions {

    int counter = 0;
    boolean reachedEnd = false;
    Mapping mapping;

    public Echo(Mapping mapping){
        this.mapping = mapping;
    } 

    @Override
    public Boolean isReached() {
        return reachedEnd;
    }

    @Override
    public String nextDecision(Drone drone, MapRepresenter map) {
        if (counter == 0){
            counter++;
            return drone.echo(drone.initialHeading);
        }
        else if (counter == 1){
            counter++;
            return drone.echo(drone.initialHeading.rightSide());
        }
        else if (counter == 2){
            counter++;
            reachedEnd = true;
            return drone.echo(drone.initialHeading.leftSide());
        }else{
            return null;
        }
    }

    @Override
    public Decisions getStage() {
         // want to know if we are found land or not
        // if we have found land, we want to go to the FindMissingDimension phase
        // else we go to the checkbehinddirection phase
        if (mapping.spawnedFacingGround){
            return new Find(mapping);
        }
        else{
            return new Locate(mapping);
        }
    }

    @Override
    public Boolean isFinal() {
        return false;
    }

    @Override
    public void processResponse(Storage responseStorage, Drone drone, MapRepresenter map) {
        // we want to process the response from the echo
        if (!(responseStorage.getCost() == null)) {
            if (responseStorage.getFound().equals("OUT_OF_RANGE")) {
                mapping.initializeMapDimensions(drone.getDirection(), responseStorage.getRange());
            } else {
                mapping.distanceToGround = responseStorage.getRange();
                mapping.spawnedFacingGround = true;
            }
            if (reachedEnd && !mapping.spawnedFacingGround) {
                mapping.initializeMapDimensions(drone.getCurrentHeading().backSide(), 0);
                mapping.initializeRowsAndColumns();
                map.initializeMap();
                drone.initializeCurrentLocation(mapping.leftColumns, mapping.topRows,mapping.spawnedFacingGround);
                mapping.directionToEcho(drone.getCurrentHeading());
            }
        }
    }

    
}
