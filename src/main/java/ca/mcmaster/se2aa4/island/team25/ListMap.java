package ca.mcmaster.se2aa4.island.team25;

import java.util.ArrayList;

public class ListMap {
    private ArrayList<InterestPoint> mainPoints;
    private int eQuant = 0;

    public ListMap() {
        // Initialize Arrya list of all points of intrest
        mainPoints = new ArrayList<InterestPoint>();
    }

    public void putPoint(String id, int x, int y, Kind typeKind) {
        InterestPoint point = new InterestPoint(id, x, y, typeKind);
        if (point.getType() == Kind.EmergencySite) {
            mainPoints.add(0, point);
            eQuant++;
        } else {
            mainPoints.add(point);
        }
    }

    public void sortPoint() {
        int size = mainPoints.size();
        for (int i = eQuant; i < size; i++) {
            InterestPoint key = mainPoints.get(i);
            int j = i - 1;

            // Move elements that are greater than key one position ahead
            while (j >= eQuant && mainPoints.get(j).distanceTo(mainPoints.get(0)) < key.distanceTo(mainPoints.get(0))) {
                mainPoints.set(j + 1, mainPoints.get(j)); // Shift element right
                j--;
            }
            mainPoints.set(j + 1, key); // Insert key at correct position
        }
    }

    private void report() {
    }

    public InterestPoint returnEmergencyPoint() {
        return mainPoints.get(0);
    }

    public InterestPoint returnCloseInterestPoint() {
        return mainPoints.get(mainPoints.size() - 1);
    }

    public void printEmergency() {
        System.out.println("ID: " + mainPoints.get(0).returnID());
        System.out.println("X: " + mainPoints.get(0).getX());
        System.out.println("Y: " + mainPoints.get(0).getY());
    }

    public void printCreeks() {
        for (int i = 1; i < mainPoints.size(); i++) {
            System.out.println("ID: " + mainPoints.get(i).returnID());
            System.out.println("X: " + mainPoints.get(i).getX());
            System.out.println("Y: " + mainPoints.get(i).getY());
            System.out.println();
        }
    }

}