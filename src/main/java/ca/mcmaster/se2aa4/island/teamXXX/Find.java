package ca.mcmaster.se2aa4.island.teamXXX;

public class Find implements Decisions {
    int distanceToGround;
    Mapping mapping;
    public Find(int distanceToGround, Mapping mapping){
        this.distanceToGround = distanceToGround;
        this.mapping = mapping;
    }

    @Override
    public Boolean isReached() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isReached'");
    }

    @Override
    public String nextDecision(Storage responseStorage, Drone drone, MapRepresenter map) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'nextDecision'");
    }

    @Override
    public Decisions getStage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStage'");
    }

    @Override
    public Boolean isFinal() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isFinal'");
    }

    @Override
    public void processResponse(Storage responseStorage, Drone drone, MapRepresenter map) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'processResponse'");
    }
    
}
