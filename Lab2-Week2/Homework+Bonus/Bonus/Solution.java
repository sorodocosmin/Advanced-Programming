package Bonus;

import Locations.Location;

import java.util.ArrayList;

public class Solution {
    private ArrayList <Location> route;

    public Solution(ArrayList<Location> route) {
        this.route = route;
    }

    public ArrayList<Location> getRoute() {
        return route;
    }

    public void setRoute(ArrayList<Location> route) {
        this.route = route;
    }

    public void showRoute(){
        if(this.route != null) {
            System.out.println("For getting from location  : \"" + this.route.get(0).getName() + "\" to location : \"" + this.route.get(this.route.size() - 1).getName() + "\" you can follow this route : ");
            for (Location loc : this.route) {
                System.out.println(loc);
            }
        }
        else{
            System.out.println("There is no route");
        }

    }
}
