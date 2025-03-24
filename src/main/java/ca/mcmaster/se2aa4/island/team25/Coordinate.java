package ca.mcmaster.se2aa4.island.team25;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Coordinate {

    private final Logger logger = LogManager.getLogger();

    private int x, y; //X and Y coordinates 

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void changeCord(Direction dir) {
        this.x += dir.changeX;
        this.y += dir.changeY;
        //logger.info(" || Drone is currently at [X:" + this.x + ", Y: "+this.y+"]");
    }

}
