package ca.mcmaster.se2aa4.island.teamXXX;

import java.util.ArrayList;
import java.util.List;
import ca.mcmaster.se2aa4.island.teamXXX.Storage;


public class Creeks extends InterestPoints {

    private List<String> identifiers = new ArrayList<String>();

    public Creeks(LocationPoint poi) {
        super(poi);
    }
    public List<String> getIdentifiers() {
        return identifiers;
    }
    
        @Override
    public void storeScanResults(Storage scanResults) {
        poi.storeScanResults(scanResults);
        for (String creekIdentifier : scanResults.getCreeks()) {
            identifiers.add(creekIdentifier);
        }
    }
    
}
