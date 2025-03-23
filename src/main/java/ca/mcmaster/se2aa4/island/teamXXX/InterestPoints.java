package ca.mcmaster.se2aa4.island.teamXXX;

import java.lang.reflect.AccessFlag.Location;
import java.util.ArrayList;
import java.util.List;
import ca.mcmaster.se2aa4.island.teamXXX.Storage;

/*looking at _pois.json we get info on this: 
 * "uid": "1f3f5c9f-8a64-4ab1-bd1a-c8fa705fec56",
        "kind": "Creek",
        "x": 560.247106714227,
        "y": 765.8454474411957
*/

public abstract class InterestPoints implements LocationPoint{
    protected LocationPoint poi;

    public InterestPoints(LocationPoint poi) {
        this.poi = poi;
    }

    @Override
    public int getRow() {
        return poi.getRow();
    }

    @Override
    public int getColumn() {
        return poi.getColumn();
    }

    @Override
    public Boolean getGround() {
        return poi.getGround();
    }

    @Override
    public void addBiomes(List<String> biomes) {
        poi.addBiomes(biomes);
    }

    @Override
    public boolean getBeenScanned() {
        return poi.getBeenScanned();
    }
    @Override
    public void setBeenScanned(boolean beenScanned) {
        poi.setBeenScanned(beenScanned);
    }
    
    // Abstract method for storeScanResults
    @Override
    public abstract void storeScanResults(Storage scanResults);
    
    
}
