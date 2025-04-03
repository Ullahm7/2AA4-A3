package ca.mcmaster.se2aa4.island.team25;

public class SearchFactory {

    public SearchMethod getSearchMethod(String searchType, Drone drone, boolean flipped){
        
        if (searchType == null){
            return null;
        }
        if(searchType.equalsIgnoreCase("FINDHOME")){
            
            return new FindHome(drone);
         
        }
        else if(searchType.equalsIgnoreCase("FULLUTURN")){

            return new FullUTurn(drone);
         
        } 
        else if(searchType.equalsIgnoreCase("UTURN")){
            
            return new UTurn(drone, flipped);
            
        }
        
        return null;
    }
    
}
