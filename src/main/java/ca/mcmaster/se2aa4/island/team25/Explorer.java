package ca.mcmaster.se2aa4.island.team25;

import java.io.StringReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;

import eu.ace_design.island.bot.IExplorerRaid;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();

    private Patroller patroller;

    @Override
    public void initialize(String s) {

        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}", info.toString(2));

        String direction = info.getString("heading");
        Integer batteryLevel = info.getInt("budget");

        ListMap map = new ListMap();
        Drone drone = new Drone(batteryLevel, direction.charAt(0));
        this.patroller = new Patroller(drone, map);

        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);
    }

    @Override
    public String takeDecision() {

        String decision = patroller.nextAction();
        return decision;
    }

    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));

        Integer cost = response.getInt("cost");
        String status = response.getString("status");
        JSONObject extraInfo = response.getJSONObject("extras");
        patroller.readAction(response, extraInfo);
    }

    @Override
    public String deliverFinalReport() {
        logger.info(this.patroller.report());
        return this.patroller.report();
    }

}
