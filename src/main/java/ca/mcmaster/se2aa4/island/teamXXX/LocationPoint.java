package ca.mcmaster.se2aa4.island.teamXXX;
import java.util.List;
import java.util.ArrayList;

public interface LocationPoint {
    
    int getRow();
    int getColumn();
    void addBiomes(List<String> biomes);
    Boolean getGround();

    void storeScanResults(Storage scanResults);
    boolean getBeenScanned();
    void setBeenScanned(boolean beenScanned);
    
}
