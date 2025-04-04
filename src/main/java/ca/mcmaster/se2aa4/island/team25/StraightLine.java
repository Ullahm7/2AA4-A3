package ca.mcmaster.se2aa4.island.team25;

import org.json.JSONObject;

public class StraightLine implements SearchMethodInfo {

    private Drone drone;

    private int counter = -1;
    private boolean scan = true;

    private boolean checkEnd = true;
    private boolean isEnd = false;

    private boolean flipped = false;
    private boolean echoAhead = true;

    private FindHomeFactory findHomeFactory;
    private SideCheckFactory sideCheckFactory;
    private FullUTurnFactory fullUTurnFactory;

    public StraightLine(Drone drone, boolean flipped) {
        
        this.drone = drone;
        this.flipped = flipped;
        this.findHomeFactory = new FindHomeFactory();
        this.sideCheckFactory = new SideCheckFactory();
        this.fullUTurnFactory = new FullUTurnFactory();
    }

    
    public JSONObject nextStep() {
        
        this.counter += 1;

        if (counter == 0) {
            return this.drone.radarDirection(this.drone.currentDir());
        }

        if (this.counter % 4 == 0) {
            return this.drone.radarDirection(this.drone.currentDir());
        }

        if (this.scan) {
            this.scan = false;
            return this.drone.simpleAction(Action.SCAN);
        } else {
            this.scan = true;
            return this.drone.simpleAction(Action.FLY);
        }
    }

    
    public void giveInfo(JSONObject info) {
        
        if (info.has("found")) {
            if (this.checkEnd) {
                this.checkEnd = false;
                this.isEnd = "GROUND".equals(info.getString("found"));
            }
          
            if ("OUT_OF_RANGE".equals(info.getString("found"))) {
                this.echoAhead = false;
            }
        }
    }

    @Override
    public SearchMethod searchType() {
        
        if (drone.goHome()) {
            
            return findHomeFactory.createSearch(this.drone, this.flipped);
        } 
        if (!this.isEnd && !this.flipped) {
            
            return fullUTurnFactory.createSearch(this.drone, this.flipped);
        }
        if (!this.isEnd && this.flipped) {
            
            return findHomeFactory.createSearch(this.drone, this.flipped);
        }
        if (!this.echoAhead) {
            
            return sideCheckFactory.createSearch(this.drone, this.flipped);
        }
        return this;
    }
}
