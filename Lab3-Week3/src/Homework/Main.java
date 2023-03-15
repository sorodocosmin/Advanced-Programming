package Homework;


import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Person ioana = new Designer("Ioana","blue",LocalDate.of(2001,01,01));
        Person mihai = new Programmer("Mihai","C++", LocalDate.of(1999,9,9));
        Person mihai1 = new Programmer("Mihai","C", LocalDate.of(1999,9,9));

        Person andrei = new Programmer("Andrei","C++", LocalDate.of(1999,10,10));
        Company A = new Company("A",3);
        Company B = new Company("B", 100);

        ioana.addRelation(mihai,"BRO1");
        ioana.addRelation(mihai1,"BRO2");

        A.addRelation(ioana,"employer");
        A.addRelation(mihai,"employer");
        A.addRelation(andrei,"employer");
        A.addRelation(B,"collaboration");

        mihai.addRelation(A, "employee");
        andrei.addRelation(A, "employee");
        ioana.addRelation(A, "employee");

        andrei.addRelation(mihai,"brother");
        mihai.addRelation(andrei,"brother");

        andrei.addRelation(B,"employee");
        B.addRelation(andrei,"employer");

        Network network = new Network();

        network.addNode(andrei);
        network.addNode(ioana);
        network.addNode(B);
        network.addNode(mihai);
        network.addNode(A);


        network.printNodes();

    }

}