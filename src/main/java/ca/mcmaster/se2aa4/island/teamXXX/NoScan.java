package ca.mcmaster.se2aa4.island.teamXXX;
import ca.mcmaster.se2aa4.island.teamXXX.*;

public class NoScan implements Decisions {

    Boolean reachedEnd = false;

    Search search;

    public NoScan(Search search) {
        this.search = search;
    }

    @Override
    public Boolean isReached() {
        return reachedEnd;
    }

    @Override
    public String nextDecision(Drone drone, MapRepresenter map) {
        if (search.distanceToFly == 0) {
            reachedEnd = true;
            return null;
        } else {
            search.distanceToFly--;
            return drone.fly();
        }
    }

    @Override
    public Decisions getStage() {
        if (search.translated){
            search.translated = false;
            return new TurnAround(search);
        }
        else{
            return new Scan(search);
        }
    }

    @Override
    public Boolean isFinal() {
        return false;
    }
    
}
