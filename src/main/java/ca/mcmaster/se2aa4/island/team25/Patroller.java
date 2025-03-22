package ca.mcmaster.se2aa4.island.team25;

import org.json.JSONObject;

public class Patroller {

    private int lastA = 2;

    public String nextAction() {
        JSONObject d = new JSONObject();
        JSONObject e = new JSONObject();

        if (this.lastA == 0) {
            d.put("action", "heading");
            e.put("direction", "S");
            d.put("parameters", e);
            return d.toString();
        } else if (this.lastA == 30) {
            d.put("action", "stop");
            return d.toString();
        } else if (this.lastA % 3 == 0) {
            d.put("action", "fly");
            return d.toString();
        } else if (this.lastA % 3 == 2) {
            d.put("action", "echo");
            e.put("direction", "N");
            d.put("parameters", e);
            return d.toString();
        } else {
            d.put("action", "scan");
            return d.toString();
        }
    }

    public void readAction(JSONObject response) {
        this.lastA += 1;
    }
}
