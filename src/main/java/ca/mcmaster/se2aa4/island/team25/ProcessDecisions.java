package ca.mcmaster.se2aa4.island.team25;

public interface ProcessDecisions extends Decisions {

    void processResponse(Storage responseStorage, Drone drone, MapRepresenter map);
    
}
