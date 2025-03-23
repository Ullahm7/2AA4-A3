package ca.mcmaster.se2aa4.island.teamXXX;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ca.mcmaster.se2aa4.island.teamXXX.Drone.Heading;
public class Mapping {
    private final Logger logger = LogManager.getLogger();

    Integer topY;
    Integer bottomY;
    Integer leftX;
    Integer rightX;

    MapRepresenter map;

    public Mapping(MapRepresenter map) {
        this.map = map;
    }

    public void initializeMapDimensions(Heading heading, Integer range) {
        switch (heading) {
            case N:
                topY = range;
                break;
            case E:
                rightX = range;
                break;
            case S:
                bottomY = range;
                break;
            case W:
                leftX = range;
                break;
            default:
                break;
        }
    }
}
