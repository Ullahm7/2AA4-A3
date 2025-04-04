package ca.mcmaster.se2aa4.island.team25;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class StraightLine implements SearchMethodInfo {

    //private final Logger logger = LogManager.getLogger();
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
        //logger.info("*** STARTING A STRAIGHT ***"+flipped);
        this.drone = drone;
        this.flipped = flipped;
        this.findHomeFactory = new FindHomeFactory();
        this.sideCheckFactory = new SideCheckFactory();
        this.fullUTurnFactory = new FullUTurnFactory();
    }

    @Override
    public JSONObject nextStep() {
        //logger.info("***STRAIGHT NEXT STEP || " + (this.counter+1));
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

    @Override
    public void giveInfo(JSONObject info) {
        //logger.info("*** Check response");
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
        //logger.info("FLIPPED: " + this.flipped);
        if (drone.goHome()) {
            //return new FindHome(this.drone);
            return findHomeFactory.createSearch(this.drone, this.flipped);
        } 
        if (!this.isEnd && !this.flipped) {
            //return new FullUTurn(this.drone);
            return fullUTurnFactory.createSearch(this.drone, this.flipped);
        }
        if (!this.isEnd && this.flipped) {
            //return new FindHome(this.drone);
            return findHomeFactory.createSearch(this.drone, this.flipped);
        }
        if (!this.echoAhead) {
            //return new SideCheck(this.drone, this.flipped);
            return sideCheckFactory.createSearch(this.drone, this.flipped);
        }
        return this;
    }
}
