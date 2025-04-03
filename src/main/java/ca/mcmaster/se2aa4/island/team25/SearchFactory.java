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
        else if(searchType.equalsIgnoreCase("INITIALSEARCH")){
            
            return new InitialSearch(drone);
            
        }
        else if(searchType.equalsIgnoreCase("SIDECHECK")){
            
            return new SideCheck(drone, flipped);
            
        }
        else if(searchType.equalsIgnoreCase("STRAIGHTLINE")){
            
            return new StraightLine(drone, flipped);
            
        }
        return null;
    }
    
}
