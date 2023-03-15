package Homework;

import java.util.Map;

public interface Node {
    public String getName();
    public void addRelation(Node node, String typeOfRelation);
    public Map<Node,String> getRelations();
}
