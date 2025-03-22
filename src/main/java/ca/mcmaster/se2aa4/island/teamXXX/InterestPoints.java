package ca.mcmaster.se2aa4.island.teamXXX;

import java.util.ArrayList;

/*looking at _pois.json we get info on this: 
 * "uid": "1f3f5c9f-8a64-4ab1-bd1a-c8fa705fec56",
        "kind": "Creek",
        "x": 560.247106714227,
        "y": 765.8454474411957
*/

public class InterestPoints extends LocationPoint{

    private String identifier;
    private String type;
    private double[] location; //contains x and y
    

    public InterestPoints(int x, int y,String identifier, String type){
        super(x,y);
        this.identifier = identifier;
        this.type = type;
        super.isPOI = true;
    }

    public String getId(){
        return identifier;
    }

    public String getType(){
        return type;
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
