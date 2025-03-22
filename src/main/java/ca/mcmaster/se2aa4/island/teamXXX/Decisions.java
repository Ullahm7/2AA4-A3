package ca.mcmaster.se2aa4.island.teamXXX;

public interface Decisions {
    Boolean reachedEnd();

    String nextDecision();

    Decisions getPhase();

    Boolean isFinal();
}
