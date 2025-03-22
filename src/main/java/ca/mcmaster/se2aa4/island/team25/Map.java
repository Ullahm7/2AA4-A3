package ca.mcmaster.se2aa4.island.team25;

public class Map {
    private ArrayList<InterestPoint> mainPoints; 
    private int eQuant = 0;

    public Map () {
        //Initialize Arrya list of all points of intrest
        mainPoints = new ArrayList<InterestPoint>();
    }

    public void putPoint(InterestPoint point){
        if (point.type == "EmergencySite"){
            mainPoints.add(0, point);
            eQuant++;
        } else {
            mainPoints.add(point);    
        }
    }

    private void sortPoint(){
        int size = mainPoints.length();
        for (int i = eQuant; i < n; i++) {
            int key = mainPoints.get(i);
            int j = i - 1;

            // Move elements that are greater than key one position ahead
            while (j >= eQuant && mainPoints.get(j).distanceTo(mainPoints.get(0)) > key.distanceTo(mainPoints.get(0))) {
                mainPoints.set(j + 1, mainPoints.get(j)); // Shift element right
                j--;
            }
            mainPoints.set(j + 1, key); // Insert key at correct position
        }
    }

}