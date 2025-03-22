package ca.mcmaster.se2aa4.island.teamXXX;
import ca.mcmaster.se2aa4.island.teamXXX.InterestPoints;
import ca.mcmaster.se2aa4.island.teamXXX.LocationPoint;
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
    public void storeScanResults(HashMap<String, List<String>> scanResults, LocationPoint currentLocation){
        // store the scan results in the map

        if (!(scanResults.get("creeks").get(0).equals("null"))){
            // if there are creeks, add them to the POI list
            for (String creekIdentifier : scanResults.get("creeks")){
                InterestPoints poi = new InterestPoints(currentLocation.getX(), currentLocation.getY(), creekIdentifier, "creek");
                pois.add(poi);
            }
        }
        if (!(scanResults.get("sites").get(0).equals("null"))){
            // if there are sites, add them to the POI list
            for (String siteIdentifier : scanResults.get("sites")){
                InterestPoints poi = new InterestPoints(currentLocation.getX(), currentLocation.getY(), siteIdentifier, "creek");
                pois.add(poi);
            }
        }

    }   
    
}


