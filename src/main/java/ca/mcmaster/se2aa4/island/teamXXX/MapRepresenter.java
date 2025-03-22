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
    public ArrayList<InterestPoints> pois = new ArrayList<>();

    /*something like (x,y)(x,y)....
     *               (x,y)(x,y)....
     *                  .   .
     *                  .   .
     * for the map
    */
    List<List<LocationPoint>> map = new ArrayList<>(); 

    //should take in the biome, creek, or sites
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


