package Compulsory;

public class Company1 implements Node1, Comparable<Company1>{
    private String name;
    private int numberEmployees;

    public Company1(String name, int nrEmployees){
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
    public String toString() {
        return this.name + "(Company)";
    }

    @Override
    public int compareTo(Company1 o) {
        return this.name.compareTo(o.name);
    }
}
