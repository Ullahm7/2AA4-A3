package ca.mcmaster.se2aa4.island.team25;

import ca.mcmaster.se2aa4.island.team25.*;


public class Fly implements Decisions {
    int counter = 0;
    boolean reachedEnd = false;

    Mapping mapping;

    public Fly(Mapping mapping) {
        this.mapping = mapping;
    }

    @Override
    public Boolean isReached() {
        return reachedEnd;
    }

    @Override
    public String nextDecision(Drone drone, MapRepresenter map) {
        if (mapping.distanceToGround == 1) {
            reachedEnd = true;
            return drone.scan();
        } else {
            mapping.distanceToGround--;
            return drone.fly();
        }
    }

    @Override
    public Decisions getStage() {
        return new Scan(new Search(mapping.drone, mapping.map));
    }

    @Override
    public Boolean isFinal() {
        return false;
    }

    
    
}
