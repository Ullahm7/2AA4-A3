package ca.mcmaster.se2aa4.island.team25;


public class Scan implements ProcessDecisions{

    Boolean reachedEnd = false;
    Boolean isFinal = false;

    // scan first then fly
    Boolean flyCheck = false;
    Boolean foundClosestCreek = false;

    Search search;

    Decisions nextDecision;

    public Scan(Search search) {
        this.search = search;
    }

    @Override
    public Boolean isReached() {
        return reachedEnd;
    }

    @Override
    public String nextDecision(Drone drone, MapRepresenter map) {
        if (search.atEdge) {
            reachedEnd = true;
            return drone.echo(drone.getCurrentHeading());
        } else if (!flyCheck) {
            flyCheck = true;
            return drone.scan();
        } else if (flyCheck) {
            flyCheck = false;
            return drone.fly();
        }
        return null;
    }

    @Override
    public Decisions getStage() {
        return nextDecision;
    }

    @Override
    public Boolean isFinal() {
        return isFinal;
    }

    @Override
    public void processResponse(Storage responseStorage, Drone drone, MapRepresenter map) {
        if (drone.getAction().equals("scan")) {
            if (!drone.getCurrentLocation().getGround()) {
                search.atEdge = true;
            }
            if (map.getSite() != null && !map.getCreeks().isEmpty()) {
                foundClosestCreek = search.foundClosestCreek(map);
                if (foundClosestCreek) {
                    reachedEnd = true;
                    isFinal = true;
                }
            }
        }
        if (drone.getAction().equals("echo")) {
            map.setAsScanned(drone, responseStorage.getRange(), drone.getCurrentHeading());
            if (responseStorage.getFound().equals("OUT_OF_RANGE")) {
                search.atEdge = true;
                if (search.gridSearchDirection == search.generalDirection.leftSide()) {
                    search.sideToTurn = "right";
                } else if (search.gridSearchDirection == search.generalDirection
                        .rightSide()) {
                    search.sideToTurn = "left";
                }
                nextDecision = new Bounds(search);
            } else {
                search.distanceToFly = responseStorage.getRange() + 1;
                search.atEdge = false;
                nextDecision = new NoScan(search);
            }
        }
    }
    
}
