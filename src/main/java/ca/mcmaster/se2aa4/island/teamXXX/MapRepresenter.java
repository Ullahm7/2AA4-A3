package ca.mcmaster.se2aa4.island.teamXXX;
import ca.mcmaster.se2aa4.island.teamXXX.*;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class MapRepresenter {
    public Boolean initialized = false;
    //simple array list for pois that we are interested in
    private List<Creeks> creeks = new ArrayList<>();
    private Sites site;
    public Creeks closestCreek;
    int columns = 0;
    int rows = 0;
    private double closestCreekDistance = 0.0;
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
                LocationPoint point = new Points(i, j);
                row.add(point);
            }
            map.add(row);
        }
    }

    //should take in the biome, creek, or sites, using the storage class
    public void storeScanResults(Storage scanResults, LocationPoint currentLocation) {

        if (!(scanResults.getCreeks().get(0).equals("null")) && !(scanResults.getSite().equals("null"))) {
            // if there are creeks, add them to the POI list
            currentLocation = new Creeks(currentLocation);
            currentLocation.storeScanResults(scanResults);
            creeks.add((Creeks) currentLocation);

            currentLocation = new Sites(currentLocation);
            currentLocation.storeScanResults(scanResults);
            site = (Sites) currentLocation;

            closestCreek = creeks.get(0);
            updateClosestCreek();
        } else if (!(scanResults.getCreeks().get(0).equals("null"))) {
            // if there are creeks, add them to the POI list
            currentLocation = new Creeks(currentLocation);
            currentLocation.storeScanResults(scanResults);
            creeks.add((Creeks) currentLocation);
            closestCreek = creeks.get(0);
            updateClosestCreek();
        } else if (!(scanResults.getSite().equals("null"))) {
            currentLocation = new Sites(currentLocation);
            currentLocation.storeScanResults(scanResults);
            site = (Sites) currentLocation;
            updateClosestCreek();
        } else {
            currentLocation.storeScanResults(scanResults);
        }
    }

    public void initializeMap() {
        // clear the map that we used for intialization purposes
        map.clear();

        // initialize the map with the given dimensions
        for (int i = 0; i < rows; i++) {
            List<LocationPoint> row = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                Points point = new Points(i, j);
                
                // set the points on the edges as scanned
                if (i <= 2 || j <= 2 || i >= rows - 3 || j >= columns - 3){
                    point.setBeenScanned(true);
                }
                row.add(point);
            }
            map.add(row);
        }
    }

    private double computeMinDistance() {
        if (site == null) {
            return 0;
        }
        closestCreek = creeks.get(0);
        double minDistance = 1000000;
        double tolerance = 0.05;
        for (Creeks creek : creeks) {
            double distance = distanceBetweenTwoPoints(creek, site);
            if (distance < minDistance) {
                closestCreek = creek;
                minDistance = distance;
            }
        }
        return minDistance;
    }

    public double distanceBetweenTwoPoints(LocationPoint point1, LocationPoint point2) {
        return Math.sqrt(Math.pow((point1.getRow() - point2.getRow()), 2)
                + Math.pow((point1.getColumn() - point2.getColumn()), 2));
    }

    public List<Creeks> getCreeks() {
        return creeks;
    }

    public Creeks getClosestCreek() {
        return closestCreek;
    }

    public Sites getSite() {
        return site;
    }

    public Double getClosestCreekDistance() {
        return closestCreekDistance;
    }

    public void updateClosestCreek() {
        if (!creeks.isEmpty() && site != null) {
            closestCreekDistance = this.computeMinDistance();
        }
    }

    public void setAsScanned(Drone drone, int distance, Heading heading) {
        LocationPoint currentLocation = drone.getCurrentLocation();
        switch (heading){
            case Heading.N:
                if (heading == drone.getCurrentHeading().backSide()){
                    distance = 0;
                }
                else{
                    distance = currentLocation.getRow() - distance;
                }
                for (int i = currentLocation.getRow(); i >= distance; i--){
                    Points normalPoint = (Points) map.get(i).get(currentLocation.getColumn());
                    normalPoint.setBeenScanned(true);
                }
                break;
            case Heading.E:
                if (heading == drone.getCurrentHeading().backSide()){
                    distance = this.columns;
                }
                else{
                    distance = currentLocation.getColumn() + distance + 1;
                }
                for (int i = currentLocation.getColumn(); i < distance; i++){
                    Points normalPoint = (Points) map.get(currentLocation.getRow()).get(i);
                    normalPoint.setBeenScanned(true);
                }
                break;
            case Heading.S:
                if (heading == drone.getCurrentHeading().backSide()){
                    distance = this.rows;
                }
                else{
                    distance = currentLocation.getRow() + distance + 1;
                }
                for (int i = currentLocation.getRow(); i < distance; i++){
                    Points normalPoint = (Points) map.get(i).get(currentLocation.getColumn());
                    normalPoint.setBeenScanned(true);
                }
                break;
            case Heading.W:
                if (heading == drone.getCurrentHeading().backSide()){
                    distance = 0;
                }
                else{
                    distance = currentLocation.getColumn() - distance;
                }
                for (int i = currentLocation.getColumn(); i >= distance; i--){
                    Points normalPoint = (Points) map.get(currentLocation.getRow()).get(i);
                    normalPoint.setBeenScanned(true);
                }
                break;
        }
        Points normalPoint = (Points) currentLocation;
        normalPoint.setBeenScanned(true);
    }
    
}


