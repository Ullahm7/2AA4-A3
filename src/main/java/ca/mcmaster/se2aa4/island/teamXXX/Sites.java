package ca.mcmaster.se2aa4.island.teamXXX;
import ca.mcmaster.se2aa4.island.teamXXX.Storage;


public class Sites extends InterestPoints {

    private String identifier;

    public Sites(LocationPoint poi) {
        super(poi);
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void storeScanResults(Storage scanResults) {
        poi.storeScanResults(scanResults);
        this.identifier = scanResults.getSite();
    }
    
}
