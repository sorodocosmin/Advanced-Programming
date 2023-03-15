package Homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Network {
    private List<Node> listOfNodes = new ArrayList<>();

    public void addNode ( Node node ){
        this.listOfNodes.add(node);
    }

    public int importanceNode(Node node){
        if(this.listOfNodes.indexOf(node) != -1){
            return node.getRelations().size();
        }

        return -1;
    }

    public void printNodes() {

        this.listOfNodes.sort(
                (node1, node2)->{
                    return (this.importanceNode(node2) - this.importanceNode(node1));
                }
                );

        for( Node node : this.listOfNodes){
            System.out.println(node);
        }
    }

}
