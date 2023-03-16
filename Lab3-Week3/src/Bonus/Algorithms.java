package Bonus;

import Homework.Network;
import Homework.Node;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Algorithms {
    private Network network ;
    private List<Node> listArticulationPoints = new ArrayList<>();
    private Map<Node,Boolean> visited = new HashMap<>();
    private Map<Node,Integer> timeEntrance = new HashMap<>();
    private Map<Node,Integer> low = new HashMap<>();

    private int timer ;

    public Algorithms (Network network){
        this.network=network;
    }
    private void populateVisitedMap(){
        for(Node node : this.network.getListOfNodes()){
            this.visited.put(node,false);
        }
    }
    private void populateEntranceTimeAndLowMap(){
        for(Node node : this.network.getListOfNodes()){
            this.timeEntrance.put(node,-1);
            this.low.put(node,-1);
        }
    }

    private void addCutPoint(Node node){
        this.listArticulationPoints.add(node);
    }

    private void dfsCustom(Node currentNode, Node parentNode){
        this.visited.put(currentNode,true);
        this.timeEntrance.put(currentNode,timer);
        this.low.put(currentNode,timer);
        this.timer ++;

        int children = 0;

        for(Map.Entry<Node,String> neighbour : currentNode.getRelations().entrySet()){
            if (!this.nodeIsNodVisited(neighbour.getKey())){
                this.low.put(currentNode,Math.min(this.low.get(currentNode).intValue(),this.timeEntrance.get(neighbour.getKey()).intValue()));
            }
            else{
                dfsCustom(neighbour.getKey(),currentNode);
                this.low.put(currentNode,Math.min(this.low.get(currentNode).intValue(),this.low.get(neighbour.getKey()).intValue()));
                if(this.low.get(neighbour.getKey())>=this.timeEntrance.get(currentNode) && parentNode!=null){
                    this.addCutPoint(currentNode);
                }
                children++;
            }
        }

        if(parentNode == null && children>1){
            this.addCutPoint(currentNode);
        }
    }
    public boolean existsArticulationPoints(){
        this.timer = 0;
        this.populateVisitedMap();
        this.populateEntranceTimeAndLowMap();

        for( int i=0; i < this.network.getListOfNodes().size(); ++i){
            if(this.visited.get(this.network.getListOfNodes().get(i))==false){
                this.dfsCustom(this.network.getListOfNodes().get(i),null);
            }
        }

        if(this.listArticulationPoints.size()==0){
            return false;
        }
        return true;
    }
    public void printCutPoints(){
        if(!this.existsArticulationPoints()){
            System.out.println("This graph dosn't have any articulation points");
        }
        else {
            System.out.println("The articulation points are : ");
            for(Node node : this.listArticulationPoints){
                System.out.println(node);
            }
        }
    }
    private boolean nodeIsNodVisited(Node node){
        return !this.visited.get(node);
    }
    private boolean nodeIsNotArticulationPoint(Node node){
        return !this.listArticulationPoints.contains(node);
    }
    private void dfs(Node node){

        this.visited.put(node,true);
        System.out.println(node);

        if(this.nodeIsNotArticulationPoint(node)){
            for( Node neighbours : node.getRelations().keySet()){
                if(this.nodeIsNodVisited(neighbours)){
                    dfs(neighbours);
                }
            }
        }
    }

    private void dfsForArticulationPoints(Node node, boolean neighbour){

        this.visited.put(node,true);
        System.out.println(node);
        if(!neighbour){
            for( Node neighbours : node.getRelations().keySet()){
                if(this.nodeIsNodVisited(neighbours)){
                    dfsForArticulationPoints(neighbours,true);
                }
            }
        }

    }
    public void printConnectedBlocks(){
        if(!this.existsArticulationPoints()){
            System.out.println("This graph dosn't have any articulation points");
        }
        this.populateVisitedMap();
        int i = 1;
        for( Node node : this.network.getListOfNodes()){
            if(this.nodeIsNotArticulationPoint(node) && this.nodeIsNodVisited(node)){
                System.out.println("\nBlock " + i + " : ");
                dfs(node);
                ++i;
            }
        }

        for( Node node : this.network.getListOfNodes()){
            if(this.nodeIsNodVisited(node)){
                System.out.println("\nBlock " + i + " : ");
                dfsForArticulationPoints(node,false);
                ++i;
            }
        }
    }

}
