package ca.mcmaster.se2aa4.island.team25;

public enum Heading {
    N, 
    E, 
    S, 
    W; 

    public Heading leftSide() {
        switch (this) {
            case N:
                return W;
            case E:
                return N;
            case S:
                return E;
            case W:
                return S;
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }

    public Heading rightSide() {
        switch (this) {
            case N:
                return E;
            case E:
                return S;
            case S:
                return W;
            case W:
                return N;
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }

    public Heading backSide() {
        switch (this) {
            case N:
                return S;
            case E:
                return W;
            case S:
                return N;
            case W:
                return E;
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }
}
