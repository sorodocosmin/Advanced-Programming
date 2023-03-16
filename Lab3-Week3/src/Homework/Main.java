package Homework;


import Bonus.Algorithms;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

       // example1();
        example2();

    }

    public static void example1(){
        Person ioana = new Designer("Ioana","blue",LocalDate.of(2001,01,01));

        Person mihai = new Programmer("Mihai","C++", LocalDate.of(1999,9,9));
        Person andrei = new Programmer("Andrei","C++", LocalDate.of(1999,10,10));

        Company A = new Company("A",3);
        Company B = new Company("B", 100);

        A.addRelation(ioana,"employer");
        A.addRelation(mihai,"employer");
        A.addRelation(andrei,"employer");
        A.addRelation(B,"collaboration");


        mihai.addRelation(andrei,"brother");

        andrei.addRelation(B,"employee");
        B.addRelation(andrei,"employer");
        B.addRelation(A,"collaboration");

        Network network = new Network();

        network.addNode(andrei);
        network.addNode(B);
        network.addNode(mihai);
        network.addNode(A);
        network.addNode(ioana);

        network.printNodes();

        Algorithms alg = new Algorithms(network);
        alg.printCutPoints();
        alg.printConnectedBlocks();
    }

    public static void example2(){
        Node node = new Programmer("person0","Java",LocalDate.of(1999,11,11));
        Network network = new Network();
        network.addNode(node);
        for( int i = 1 ; i < 100; ++i){
            Node node2 = new Programmer("person"+i,"Java",LocalDate.of(1999,11,11));
            node2.addRelation(node,"Friends");
            network.addNode(node2);
            node = node2;
        }

        Algorithms alg = new Algorithms(network);
        alg.printCutPoints();
        alg.printConnectedBlocks();
    }

}