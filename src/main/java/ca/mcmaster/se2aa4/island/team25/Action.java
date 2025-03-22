package ca.mcmaster.se2aa4.island.team25;

//Stores direction and icon
public enum Action {

    //Stores change in coordinate for each direction
    //And the icon of the direction for debuging maze mode
    FLY("fly"),
    SCAN("scan"),
    STOP("stop");

    //cannot be changed for saftey
    final String term;

    //Sets variables for ENUMS
    Action(String term) {
        this.term = term;
    }
}
