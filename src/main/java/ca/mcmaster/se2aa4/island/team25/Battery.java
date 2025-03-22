package ca.mcmaster.se2aa4.island.team25;


public class Battery {

    private int initialBattery;
    private int battery;
    private int batteryToLoc;

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
    }



}