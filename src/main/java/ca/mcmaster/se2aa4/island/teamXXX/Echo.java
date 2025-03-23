package ca.mcmaster.se2aa4.island.teamXXX;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Echo implements Decisions {

    private final Logger logger = LogManager.getLogger();

    int counter = 0;
    boolean reachedEnd = false;
    int distanceToGround = 0;
    Mapping mapping;

    public Echo(Mapping mapping){
        this.mapping = mapping;
    } 

    @Override
    public Boolean isReached() {
        return reachedEnd;
    }

    @Override
    public String nextDecision(Storage responseStorage, Drone drone, MapRepresenter map) {
        logger.info(drone.getBatteryLevel());
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
                drone.initializeCurrentLocation(mapping.leftX, mapping.topY,mapping.spawnedFacingGround);
                mapping.directionToEcho(drone.getCurrentHeading());
            }
        }
    }
    
}
