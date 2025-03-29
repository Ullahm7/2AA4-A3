package ca.mcmaster.se2aa4.island.team25;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ListMapIterator implements Iterator {

    private List<InterestPoint> interestPoints;
    private int currentIndex = 0;

    public ListMapIterator(ArrayList<InterestPoint> interestPoints) {
        this.interestPoints = interestPoints;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < interestPoints.size();
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException(); //or return null
        }
        return interestPoints.get(currentIndex++);
    }
    
}
