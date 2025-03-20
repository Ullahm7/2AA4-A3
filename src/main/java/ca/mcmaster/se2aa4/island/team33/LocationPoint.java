package ca.mcmaster.se2aa4.island.teamXXX;

public class LocationPoint {
    
    private int x;
    private int y; 
    boolean isGround;
    boolean isPOI;

    public LocationPoint(int x, int y) {

        this.x = x;
        this.y = y;

    }

    public int getX(){

        return x;

    }
    public int getY(){

        return y;
        
    }
}
