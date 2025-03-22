package ca.mcmaster.se2aa4.island.team25;

public interface Point {

    String returnID();
    double getX();
    double getY();
    double distanceTo(Point p);

}