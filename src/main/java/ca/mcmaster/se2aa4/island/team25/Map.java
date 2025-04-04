package ca.mcmaster.se2aa4.island.team25;

import java.util.ArrayList;

public class Map implements Aggregate<InterestPoint> {

    private ArrayList<InterestPoint> interestPoints;

    public Map(ArrayList<InterestPoint> interestPoints) {
        this.interestPoints = interestPoints;
    }

    public Iterator createIterator() {
        return new ListMapIterator(interestPoints);
    }
    
}
