package ca.mcmaster.se2aa4.island.team25;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Battery {

    private final Logger logger = LogManager.getLogger();

    private int initialBattery;
    private int battery;

    public Battery(int value)
    {
        this.initialBattery = value;
        this.battery = initialBattery;
    }

    public int currentBattery() {
        return this.battery;
    }

    public void lowerBattery(int usage) {
        this.battery -= usage;
        logger.info("  || Current battery: " + this.battery);
    }



}