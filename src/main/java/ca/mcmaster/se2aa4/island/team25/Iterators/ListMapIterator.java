package ca.mcmaster.se2aa4.island.team25.Iterators;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import ca.mcmaster.se2aa4.island.team25.InterestPoint;

public class ListMapIterator implements Iterator {

    private List<InterestPoint> interestPoints;
    private int currentIndex = 0;

    public ListMapIterator(ArrayList<InterestPoint> interestPoints) {
        this.interestPoints = interestPoints;
    }

    public boolean hasNext() {
        return currentIndex < interestPoints.size();
    }

    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException(); 
        }
        return interestPoints.get(currentIndex++);
    }
    
}
