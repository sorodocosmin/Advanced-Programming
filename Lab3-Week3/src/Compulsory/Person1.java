package Compulsory;

public abstract class Person1 implements Comparable<Person1>, Node1{
    protected String name;

    @Override
    public abstract int compareTo(Person1 person);

}
