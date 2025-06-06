package ca.mcmaster.se2aa4.island.team25;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team25.Iterators.Iterator;
import ca.mcmaster.se2aa4.island.team25.Iterators.Map;

public class ListMap {
    
    private ArrayList<InterestPoint> mainPoints;
    private int eQuant = 0;

    private final Logger logger = LogManager.getLogger();

    public ListMap() {
        //initialize Arrya list of all points of intrest
        mainPoints = new ArrayList<InterestPoint>();
    }

    public void putPoint(String id, int x, int y, Kind typeKind) {
        InterestPoint point = new InterestPoint(id, x, y, typeKind);
        if (point.getType() == Kind.EmergencySite) {
            mainPoints.add(0, point);
            eQuant++;
        } else {
            mainPoints.add(point);
        }
    }

    public void sortPoint() {
        int size = mainPoints.size();
        for (int i = eQuant; i < size; i++) {
            //use Map to create an iterator for sorting
            Map map = new Map(mainPoints);
            Iterator<InterestPoint> iterator = map.createIterator();
            
            //advance the iterator to the current element (key)
            for (int k = 0; k < i; k++) {
                iterator.next();
            }
            InterestPoint key = iterator.next();
            int j = i - 1;

            //create a new iterator for the inner while loop
            Iterator<InterestPoint> jIterator = map.createIterator();
            
            //advance the second iterator to the current position
            for (int k = 0; k < j; k++) {
                jIterator.next();
            }

            //move elements that are greater than key one position ahead
            while (j >= eQuant && jIterator.hasNext()) {
                InterestPoint previous = jIterator.next();
                if (previous.distanceTo(mainPoints.get(0)) < key.distanceTo(mainPoints.get(0))) {
                    j--;
                } else {
                    break; 
                }
            }
            
            //remove and insert the key at correct position
            if (j + 1 != i) {
                mainPoints.remove(i); 
                mainPoints.add(j + 1, key); 
            }
        }
    }

    public String returnEmergencyPoint() {
        if (mainPoints.get(0).getType() == Kind.EmergencySite) {
            return "\nEMERGENCY SITE ID " + mainPoints.get(0).returnID();
        }
        return "";
    }

    public String returnCloseInterestPoint() {
        return mainPoints.get(mainPoints.size() - 1).returnID();
    }

    public void printEmergency() {
        if (mainPoints.size() != 0) {
            if (mainPoints.get(0).getType() == Kind.EmergencySite) {
                logger.info("\nEMERGENCY ID: " + mainPoints.get(0).returnID());
                logger.info("X: " + mainPoints.get(0).getX());
                logger.info("Y: " + mainPoints.get(0).getY());
            }
        }
    }

    public void printCreeks() {
        if (mainPoints.size() != 0) {
            for (int i = 1; i < mainPoints.size(); i++) {
                logger.info("\nID: " + mainPoints.get(i).returnID());
                logger.info("X: " + mainPoints.get(i).getX());
                logger.info("Y: " + mainPoints.get(i).getY());
            }
        }
    }

}