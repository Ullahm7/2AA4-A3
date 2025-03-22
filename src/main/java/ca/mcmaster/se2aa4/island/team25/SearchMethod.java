package ca.mcmaster.se2aa4.island.team25;

import org.json.JSONObject;

public interface SearchMethod {

    JSONObject nextStep();
    void giveInfo(JSONObject info);


}