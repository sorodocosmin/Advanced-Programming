package Homework;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class Person implements Comparable<Person>, Node{
    protected String name;
    protected LocalDate dateOfBirth;

    protected Map<Node,String> relations = new HashMap<>() ;

    @Override
    public abstract int compareTo(Person person);

    @Override
    public void addRelation(Node node, String typeOfRelation){
        this.relations.put(node,typeOfRelation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(this.name, person.name);
    }

    @Override
    public Map<Node, String> getRelations() {
        return this.relations;
    }
}
