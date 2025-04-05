package ca.mcmaster.se2aa4.island.team25.Iterators;

import java.util.ArrayList;

import ca.mcmaster.se2aa4.island.team25.InterestPoint;

public class Map implements Aggregate<InterestPoint> {

    private ArrayList<InterestPoint> interestPoints;

    public Map(ArrayList<InterestPoint> interestPoints) {
        this.interestPoints = interestPoints;
    }

    public Iterator createIterator() {
        return new ListMapIterator(interestPoints);
    }
    
}
