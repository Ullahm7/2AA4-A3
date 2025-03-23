package ca.mcmaster.se2aa4.island.teamXXX;

public interface Decisions {
    Boolean isReached();

    String nextDecision(Storage responseStorage, Drone drone, MapRepresenter map);

    Decisions getStage();

    Boolean isFinal();

    void processResponse(Storage responseStorage, Drone drone, MapRepresenter map);

}
