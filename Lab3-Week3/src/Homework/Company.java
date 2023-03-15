package Homework;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Company implements Node{
    private String name;
    private int numberEmployees;

    private Map<Node, String> relations = new HashMap<>();

    public Company(String name, int nrEmployees){
        this.setName(name);
        this.setNumberEmployees(nrEmployees);
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getNumberEmployees() {
        return numberEmployees;
    }

    public void setNumberEmployees(int numberEmployees) {
        this.numberEmployees = numberEmployees;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addRelation(Node node, String typeOfRelation) {
        this.relations.put(node,typeOfRelation);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(this.name);
        result.append("(Company) : ");

        for ( Map.Entry<Node, String> relation : this.relations.entrySet()){
            result.append(relation.getKey().getName());
            result.append("=");
            result.append(relation.getValue());
            result.append("; ");
        }

        return result.toString();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return numberEmployees == company.numberEmployees && Objects.equals(name, company.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, numberEmployees);
    }

    @Override
    public Map<Node, String> getRelations() {
        return this.relations;
    }
}
