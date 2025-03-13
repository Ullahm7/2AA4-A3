package ca.mcmaster.se2aa4.island.teamXXX;

import java.util.ArrayList;

/*looking at _pois.json we get info on this: 
 * "uid": "1f3f5c9f-8a64-4ab1-bd1a-c8fa705fec56",
        "kind": "Creek",
        "x": 560.247106714227,
        "y": 765.8454474411957
*/

public class InterestPoints {

    private String id;
    private String kind;
    private double[] location; //contains x and y

    public InterestPoints(String id, String type, double[] location){
        this.id = id;
        this.kind = kind;
        this.location = location;
    }

    public String getId(){
        return null;
    }

    public String getKind(){
        return null;
    }

    public double[] getLocation(){
        return null;
    }
    
    public void setIdentifier(String identifier){
        
    }

   
    public ArrayList<String> getInfo(){
        return null;

    }
    
}
