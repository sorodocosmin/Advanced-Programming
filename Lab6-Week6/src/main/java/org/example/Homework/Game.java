package org.example.Homework;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game implements Serializable {
    public static final Color colorPlayer1 = Color.RED, colorPlayer2 = Color.BLUE;
    private int[] coordinateX, coordinateY ; //the coordinates for each node
    private boolean isPlayer1Turn = true, gameIsFinished = false;
    private Map<Integer, List<Edge>> adjacencyList;//every for every node, it will correspond a list of edges because it will be easier to check if one edge is colored
    private int nrVertices;
    //@JSONignore
    private Edge lastEdgeColored;
    public Game(){

    }
    public Game(int nrVertices, int probability, int width, int height){
        this.nrVertices = nrVertices;

        this.coordinateX = new int[nrVertices];
        this.coordinateY = new int[nrVertices];

        this.adjacencyList = new HashMap<>();
        for(int i=0;i<nrVertices;++i){
            this.adjacencyList.put(i, new ArrayList<>());
        }

        this.createVertices(width, height);
        this.createEdges(probability);


    }

    public int[] getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int[] coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int[] getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int[] coordinateY) {
        this.coordinateY = coordinateY;
    }

    public boolean isPlayer1Turn() {
        return isPlayer1Turn;
    }

    public void setPlayer1Turn(boolean player1Turn) {
        isPlayer1Turn = player1Turn;
    }

    public Map<Integer, List<Edge>> getAdjacencyList() {
        return adjacencyList;
    }

    public void setAdjacencyList(Map<Integer, List<Edge>> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    public int getNrVertices() {
        return nrVertices;
    }

    public void setNrVertices(int nrVertices) {
        this.nrVertices = nrVertices;
    }

    public boolean getGameIsFinished() {
        return gameIsFinished;
    }

    public void setGameIsFinished(boolean gameIsFinished) {
        this.gameIsFinished = gameIsFinished;
    }

    private void createVertices(int width, int height){
        int x0 = width/2 ; int y0 = height/2;
        int radius = height/2 - 10;

        double alpha = 2 * Math.PI / nrVertices; //the angle

        for( int i = 0; i< this.nrVertices ; ++i) {
            this.coordinateX[i] = x0 + (int) (radius * Math.cos(alpha * i));
            this.coordinateY[i] = y0 + (int) (radius * Math.sin(alpha * i));
        }
    }

    private void createEdges(int probability ){

        for (int i = 0; i < this.nrVertices - 1; ++i) {
            for (int j = i + 1; j < this.nrVertices; j++) {
                int randomProb = (int) (Math.random() * 100);
                if (randomProb <= probability) {//create edge
                    Edge edge1 = new Edge(i,j);
                    Edge edge2 = new Edge(j,i);
                    this.adjacencyList.get(i).add(edge1);//for the arrayList of node i, we add the node j as well;
                    this.getAdjacencyList().get(j).add(edge2);
                }
            }
        }

    }


    public void playerClicked(int mouseX, int mouseY, Graphics2D graphics){
        //check if there was the click was made on an edge
        boolean ok = false;
        for( int i = 0 ; i < this.nrVertices && !ok; ++i){
            for(Edge edge : this.adjacencyList.get(i)){
                if(!edge.isColored()){
                    if(isPointOnLineSegment(this.coordinateX[i],this.coordinateY[i],this.coordinateX[edge.getNode2()],this.coordinateY[edge.getNode2()],mouseX,mouseY)){
                        ok = true;//if an edge was selected, we color it with the respective color
                        edge.setColored(true);//the edge ij and also edge ji
                        for( Edge edgeReverse : this.adjacencyList.get(edge.getNode2())){
                            if(edgeReverse.getNode2() == i){
                                edgeReverse.setColored(true);
                                if(this.isPlayer1Turn){//set the specific color for edges
                                    graphics.setColor(colorPlayer1);
                                    edge.setColor(colorPlayer1);
                                    edgeReverse.setColor(colorPlayer1);
                                    this.isPlayer1Turn = false;
                                }
                                else{
                                    graphics.setColor(colorPlayer2);
                                    edge.setColor(colorPlayer2);
                                    edgeReverse.setColor(colorPlayer2);
                                    this.isPlayer1Turn = true;
                                }
                                break;
                            }
                        }
                        this.lastEdgeColored = edge;
                        graphics.drawLine(this.coordinateX[i],this.coordinateY[i],this.coordinateX[edge.getNode2()],this.coordinateY[edge.getNode2()]);
                        graphics.setColor(Color.GRAY);
                        if(this.formedATriangle()) {
                            this.gameIsFinished = true;
                        }
                        break;
                    }
                }
            }
        }

    }

    boolean isPointOnLineSegment(int x1, int y1, int x2, int y2, int a1, int a2) {
        //double slope = (double) (this.coordinateY[j] - this.coordinateY[i])/(this.coordinateX[j]-this.coordinateX[i]);// m = (y2-y1) / (x2-x1)
        double slope = (double) (y2 - y1) / (x2 - x1);


        double yOnOx = y1 - slope * x1;// y = mx +b;

        // Calculate the y-coordinate of the point A on the line passing through X and Y
        double expectedA2 = slope * a1 + yOnOx;

        // Check if the y-coordinate of point A is equal to a2
        if (Math.abs(expectedA2 - a2) > 10) {
            return false;
        }

        // Check if the x-coordinate of point A is between x1 and x2
        if ((a1 >= x1 && a1 <= x2) || (a1 <= x1 && a1 >= x2)) {
            return true;
        }

        return false;
    }

    boolean formedATriangle(){//the only possibility for a triangle to be formed needs to contain the last colored edge
        //we verify if there aren't a colored edge which goes into the same node (from lastColoredNode.node2() and lastColoredNode.node1())
        //lastEdgeColored will never be null
        int k = 0;

        int []neighbours1Colored = new int[ this.adjacencyList.get(this.lastEdgeColored.getNode1()).size()];
        for(Edge e : this.adjacencyList.get(this.lastEdgeColored.getNode1())){
            if(e.isColored()) {
                if( e.getColor().equals(lastEdgeColored.getColor())) {
                    neighbours1Colored[k] = e.getNode2();
                    k++;
                }
            }
        }
        for( Edge e : this.adjacencyList.get(this.lastEdgeColored.getNode2())){
            if(e.isColored()){
                if(e.getColor().equals(lastEdgeColored.getColor())){//verify if those nodes don't have a common neighbour
                    for( int i=0; i< k ; ++i ){
                        if(neighbours1Colored[i] == e.getNode2()){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void resetGame(){
        this.gameIsFinished = false;
        for( int i=0 ; i< this.nrVertices ; ++i){
            for( Edge e : this.adjacencyList.get(i)){
                e.setColored(false);
            }
        }
    }

    //no usage!
    private boolean isOnLine(int x1,int y1, int x2,int y2,int x3,int y3){//it doesn't work, why ?
        //equation : (y-y1) / (y2-y1) == (x-x1) / (x2-x1) --> y(x2 - x1) - y1(x2 - x1) == x (y2-y1) - x1(y2-y1) --> y = ( x(y2-y1) - x1(y2-y1) + y1(x2-x1) ) / (x2 - x1)
        double expectedY1 = (double) (x1*(y3-y2) - x2*(y2-y1) + y2*(x3-x2) ) / (x3-x2);
        System.out.println("Check if y=" + y1 + " = expect = " + expectedY1);
        if(Math.abs(y1-expectedY1) < 1){
            return true;
        }

        return false;
    }

}
