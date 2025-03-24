package ca.mcmaster.se2aa4.island.team25;

public interface Decisions {
    Boolean isReached();

    String nextDecision(Drone drone, MapRepresenter map);

    Decisions getStage();

    Boolean isFinal();

}
