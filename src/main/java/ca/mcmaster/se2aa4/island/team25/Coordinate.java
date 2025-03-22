package ca.mcmaster.se2aa4.island.team25;

public class Coordinate {
    private int x,y; //X and Y coordinates 

    public Coordinate (int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void changeCord(Direction dir){
        this.x += dir.changeX;
        this.y += dir.changeY;
    }

}