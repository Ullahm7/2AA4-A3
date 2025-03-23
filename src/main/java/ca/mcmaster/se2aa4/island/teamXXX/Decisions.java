package ca.mcmaster.se2aa4.island.teamXXX;

public interface Decisions {
    Boolean isReached();

    String nextDecision(Drone drone, MapRepresenter map);

    Decisions getStage();

    Boolean isFinal();

}
