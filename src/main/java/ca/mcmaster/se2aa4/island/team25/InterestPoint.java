package ca.mcmaster.se2aa4.island.team25;

import java.util.ArrayList;

/*looking at _pois.json we get info on this: 
 * "uid": "1f3f5c9f-8a64-4ab1-bd1a-c8fa705fec56",
        "kind": "Creek",
        "x": 560.247106714227,
        "y": 765.8454474411957
*/

public class InterestPoint implements Point {

    private final String identifier;
    private double x;
    private double y;
    private final Kind type;

    public InterestPoint(String identifier, double x, double y, Kind type) {
        this.identifier = identifier;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public String returnID() {
        return this.identifier;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public Kind getType() {
        return type;
    }

    public double distanceTo(Point p) {
        return Math.sqrt(Math.pow(this.x - p.getX(), 2) + Math.pow(this.y - p.getY(), 2));
    }

}
