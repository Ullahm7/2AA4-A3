package ca.mcmaster.se2aa4.island.teamXXX;
import ca.mcmaster.se2aa4.island.teamXXX.InterestPoints;
import ca.mcmaster.se2aa4.island.teamXXX.LocationPoint;
import eu.ace_design.island.game.PointOfInterest;
import ca.mcmaster.se2aa4.island.teamXXX.Drone.Heading;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class MapRepresenter {
    private final Logger logger = LogManager.getLogger();
    public Boolean initialized = false;
    //simple array list for pois that we are interested in
    public List<InterestPoints> pois = new ArrayList<>();
    public List<InterestPoints> creeks = new ArrayList<>();
    public InterestPoints closestCreek;
    public InterestPoints site;
    int columns = 0;
    int rows = 0;

    /*something like (x,y)(x,y)....
     *               (x,y)(x,y)....
     *                  .   .
     *                  .   .
     * for the map
    */
    List<List<LocationPoint>> map = new ArrayList<>(); 

    public MapRepresenter() {
        //initialize with these dimensions, will need to refactor for different maps (need to get dimensions)
        for (int i = 0; i < 200; i++) {
            List<LocationPoint> row = new ArrayList<>();
            for (int j = 0; j < 200; j++) {
                LocationPoint point = new LocationPoint(i, j);
                row.add(point);
            }
            map.add(row);
        }
    }

    //should take in the biome, creek, or sites, using the storage class
    public void storeScanResults(Storage scanResults, LocationPoint currentLocation) {

        if (!(scanResults.getCreeks().get(0).equals("null"))) {
            // if there are creeks, add them to the POI list
            for (String creekIdentifier : scanResults.getCreeks()) {
                InterestPoints poi = new InterestPoints(currentLocation.getX(), currentLocation.getY(),creekIdentifier,"creek");
                creeks.add(poi);
            }
        }

        if (!(scanResults.getSite().equals("null"))) {
            // if there are sites, add them to the POI list
            site = new InterestPoints(currentLocation.getX(), currentLocation.getY(),
                        scanResults.getSite(), "site");
        }
        currentLocation.addBiomes(scanResults.getBiomes());
    }

    public void initializeMap() {
        // clear the map that we used for intialization purposes
        map.clear();

        // initialize the map with the given dimensions
        logger.info("Initializing map with dimensions: " + columns + "x" + rows);
        for (int i = 0; i < columns; i++) {
            List<LocationPoint> row = new ArrayList<>();
            for (int j = 0; j < rows; j++) {
                LocationPoint point = new LocationPoint(i, j);
                row.add(point);
            }
            map.add(row);
        }

    }

    public double computeDistance(){
        if (site == null){
            return 0;
        }
        closestCreek = creeks.get(0);
        double minDistance = 1000000;
        for (InterestPoints creek : creeks){
            double distance = Math.sqrt(Math.pow((creek.getX() - site.getX()), 2) + Math.pow((creek.getY() - site.getY()), 2));
            if (distance < minDistance){
                closestCreek = creek;
                minDistance = distance;
            }
        }
        return minDistance;
    }  
    
}


