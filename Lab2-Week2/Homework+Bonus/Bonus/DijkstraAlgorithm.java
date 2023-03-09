package Bonus;

import Locations.Location;
import Main.Problem;
import Main.Road;

import java.util.ArrayList;
import java.util.Collections;

public class DijkstraAlgorithm extends Algorithm{

    public DijkstraAlgorithm(Problem problem, Location location1, Location location2){
        this.problem = problem;
        this.location1 = location1;
        this.location2 = location2;
    }
    @Override
    boolean problemIsValid() {
        if (!this.problem.isValid()){
            System.out.println("The instance for the problem is not Valid");
            return false;
        }
        if(this.location1.equals(this.location2)){
            System.out.println("The start location is the same as the final one");
            return false;
        }
        if(!this.problem.locationExists(this.location1) || !this.problem.locationExists(this.location2)){
            System.out.println("Both locations need to belong to the list of locations of the instance of the problem");
            return false;
        }

        return true;
    }

    private int indexFirstLocation(Road road){
        return this.problem.getArrayLocations().indexOf(road.getLocation1());
    }

    private int indexSecondLocation(Road road){
        return this.problem.getArrayLocations().indexOf(road.getLocation2());
    }

    private void populateLengthLocationsMatrix( double [][] costLengthLocationsMatrix ){
        for (Road road : this.problem.getArrayRoads() ){
            if( costLengthLocationsMatrix[this.indexFirstLocation(road)][this.indexSecondLocation(road)] == 0 ||
                    costLengthLocationsMatrix[this.indexFirstLocation(road)][this.indexSecondLocation(road)] > road.getLengthInKm() ){

                costLengthLocationsMatrix[this.indexFirstLocation(road)][this.indexSecondLocation(road)] =
                        costLengthLocationsMatrix[this.indexSecondLocation(road)][this.indexFirstLocation(road)] =
                                road.getLengthInKm();
            }
        }
    }

    private void populateTimeLocationsMatrix( double [][] costTimeLocationsMatrix ){
        for (Road road : this.problem.getArrayRoads() ){
            if( costTimeLocationsMatrix[this.indexFirstLocation(road)][this.indexSecondLocation(road)] == 0 ||
                    costTimeLocationsMatrix[this.indexFirstLocation(road)][this.indexSecondLocation(road)] > (road.getLengthInKm()/ road.getSpeedLimit()) ){

                costTimeLocationsMatrix[this.indexFirstLocation(road)][this.indexSecondLocation(road)] =
                        costTimeLocationsMatrix[this.indexSecondLocation(road)][this.indexFirstLocation(road)] =
                                road.getLengthInKm()/road.getSpeedLimit();
            }
        }
    }

    private int[] findClosestNode(boolean [] visited, double [] distance, double [][] matrixOfCosts){

        double minValue = Integer.MAX_VALUE;
        int [] closestNodeAndItsParent = {-1,-1};
        for (int i=0 ; i < visited.length ; ++i){
            if(visited[i]){
                for( int j = 0 ;j < matrixOfCosts[0].length; ++j){
                    if(!visited[j] && matrixOfCosts[i][j]!=0 && minValue > distance[i] + matrixOfCosts[i][j]){
                        minValue = matrixOfCosts[i][j] + distance[i];
                        closestNodeAndItsParent[0] = j;
                        closestNodeAndItsParent[1] = i;
                    }
                }
            }
        }
        if(closestNodeAndItsParent[0]!= -1){
            distance[closestNodeAndItsParent[0]] = minValue;
        }
        return closestNodeAndItsParent;
    }
    private boolean verifyIfWeCanApplyAlgorithm(){
        if( !this.problemIsValid()){
            System.out.println("The instance of the problem is not correct");
            return false;
        }

        if(!problem.goFromXtoY(location1,location2)){
            System.out.println("There isn't any path between location : " + this.location1 + " and location : " + this.location2);
            return false;
        }

        return true;
    }

    private ArrayList<Location> applyDijkstra(double [][] costMatrix){

        int nrLocations = this.problem.getArrayLocations().size();

        boolean [] visited = new boolean[nrLocations];
        double [] distance = new double[nrLocations];
        int [] parent = new int [nrLocations];

        for( int i = 0 ; i < parent.length ; ++i){
            parent[i]=-1;
        }


        int startNode = this.problem.getArrayLocations().indexOf(this.location1);

        visited[startNode] = true;

        while ( startNode != -1) {

            int[] nextNodeAndItsParent = findClosestNode(visited, distance, costMatrix);
            if (nextNodeAndItsParent[0] != -1) {
                visited[nextNodeAndItsParent[0]] = true;
                parent[nextNodeAndItsParent[0]] = nextNodeAndItsParent[1];

            }
            startNode = nextNodeAndItsParent[0];
        }

        ArrayList<Location> shortestPath = new ArrayList<>();
        shortestPath.add(location2);
        int indexLastLocation = problem.getArrayLocations().indexOf(location2);
        while (parent[indexLastLocation] != -1) {
            indexLastLocation = parent[indexLastLocation];
            shortestPath.add(this.problem.getArrayLocations().get(indexLastLocation));
        }

        Collections.reverse(shortestPath);

        return shortestPath;
    }
    @Override
    public ArrayList<Location> solveForShortestPath() {

        if ( !this.verifyIfWeCanApplyAlgorithm() ){
            return null;
        }

        int nrLocations = this.problem.getArrayLocations().size();

        double [][] costLengthLocationsMatrix = new double [nrLocations] [nrLocations];

        this.populateLengthLocationsMatrix(costLengthLocationsMatrix);

        return this.applyDijkstra(costLengthLocationsMatrix);

    }

    @Override
    public ArrayList<Location> solveForShortestTime() {

        if ( !this.verifyIfWeCanApplyAlgorithm() ){
            return null;
        }

        int nrLocations = this.problem.getArrayLocations().size();

        double [][] costTimeLocationsMatrix = new double [nrLocations] [nrLocations];

        this.populateTimeLocationsMatrix(costTimeLocationsMatrix);

        return this.applyDijkstra(costTimeLocationsMatrix);
    }
}
