package Main;

import Locations.Location;

import java.util.ArrayList;
import java.util.Stack;

public class Problem {
    private ArrayList<Location> arrayLocations;
    private ArrayList<Road> arrayRoads;

    public Problem(ArrayList<Location> arrayLocations, ArrayList<Road> arrayRoads) {
        this.arrayLocations = new ArrayList<>();
        this.arrayRoads = new ArrayList<>();

        for (Location location : arrayLocations) {
            this.addLocation(location);
        }

        for (Road road : arrayRoads) {
            this.addRoad(road);
        }
    }

    public Problem(){
        this.arrayLocations = new ArrayList<>();
        this.arrayRoads = new ArrayList<>();
    }

    public ArrayList<Location> getArrayLocations() {
        return arrayLocations;
    }

    public void setArrayLocations(ArrayList<Location> arrayLocations) {
        this.arrayLocations = arrayLocations;
    }

    public ArrayList<Road> getArrayRoads() {
        return arrayRoads;
    }

    public void setArrayRoads(ArrayList<Road> arrayRoads) {
        this.arrayRoads = arrayRoads;
    }
/**Every time when we add a new location, we will verify if there isn't already the same location added
 * */
    public void addLocation(Location location){
        if (this.arrayLocations.indexOf(location) == -1 ){
            this.arrayLocations.add(location);
        }
    }

    /**Every time when we add a new road, we will verify if there isn't already the same road added
     * */
    public void addRoad(Road road){
        if (this.arrayRoads.indexOf(road) == -1){
            this.arrayRoads.add(road);
        }
    }

    public boolean locationExists(Location location){
        if (this.arrayLocations.indexOf(location) == -1) {
            return false;
        }
        return true;
    }

    /**The problem will be valid if every road has a reasonable length
     * and there aren't 2 locations which connect a road and doesn't appear in listLocations
     * */
    public boolean isValid (){

        for (Road road : this.arrayRoads) {
            if (road.hasReasonableLength() == false) {
                return false;
            }
            if(this.locationExists(road.getLocation1())==false || this.locationExists(road.getLocation2())==false){
                return false;
            }
        }
        return true;
    }

    private void populateMatrix( int [][] matrixOfLocations){
        for( Road road : arrayRoads){
            matrixOfLocations[this.arrayLocations.indexOf(road.getLocation1())][this.arrayLocations.indexOf(road.getLocation2())]=
                    matrixOfLocations[this.arrayLocations.indexOf(road.getLocation2())][this.arrayLocations.indexOf(road.getLocation1())]
                            =1;
        }
    }
    private int nextUnvisitedNode(int node, boolean [] visited, int [][]matrix){
        for(int i=0;i<matrix[0].length ; ++i){
            if(matrix[node][i]==1 && visited[i]==false){
                return i;
            }
        }
        return -1;//if all it's neighbours were visited
    }
    private boolean existsPathFromXtoY(int location1, int location2 , int [][] matrix){
        boolean [] visited = new boolean [matrix.length];
        Stack<Integer> stack = new Stack<>();

        stack.push(location1);
        visited[location1]=true;

        while(!stack.isEmpty()){
            int currentNode = stack.peek();
            int nextNode = nextUnvisitedNode(currentNode,visited,matrix);
            visited[currentNode]=true;
            if(currentNode == location2){
                return true;
            }
            if(nextNode == -1){
                stack.pop();
            }
            else{
                stack.push(nextNode);
            }
        }

        return false;
    }
    public boolean goFromXtoY(Location locationX, Location locationY){

        int [][] matrixOfLocations = new int [this.arrayLocations.size()][this.arrayLocations.size()];

        this.populateMatrix(matrixOfLocations);

        return existsPathFromXtoY(this.arrayLocations.indexOf(locationX),this.arrayLocations.indexOf(locationY),matrixOfLocations);
    }

    @Override
    public String toString() {
        return "Problem{" +
                "arrayLocations=" + arrayLocations +
                ", arrayRoads=" + arrayRoads +
                '}';
    }
}
