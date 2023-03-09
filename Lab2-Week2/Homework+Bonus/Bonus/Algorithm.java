package Bonus;

import Locations.Location;
import Main.Problem;

import java.util.ArrayList;

public abstract class Algorithm {
    protected Problem problem;
    protected Location location1;
    protected Location location2;
    abstract boolean problemIsValid();
    public abstract ArrayList<Location> solveForShortestPath();
    public abstract ArrayList<Location> solveForShortestTime();
}
