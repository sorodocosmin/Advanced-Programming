package Bonus;

import Homework.Network;
import Homework.Node;
import Homework.Person;
import Homework.Programmer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmsTest {
    @Test
    void Test1(){
        Node node = new Programmer("person0","Java",LocalDate.of(1999,11,11));
        Network network = new Network();
        network.addNode(node);
        for( int i = 1 ; i < 1_000; ++i){
            Node node2 = new Programmer("person"+i,"Java",LocalDate.of(1999,11,11));
            node2.addRelation(node,"Friends");
            network.addNode(node2);
            node = node2;
        }

        Algorithms alg = new Algorithms(network);

        Assertions.assertEquals(true,alg.existsArticulationPoints());
    }

    @Test
    void Test2(){
        Node node = new Programmer("person0","Java",LocalDate.of(1999,11,11));
        Node node1 = node;
        Network network = new Network();
        network.addNode(node);
        for( int i = 1 ; i < 1_000; ++i){
            Node node2 = new Programmer("person"+i,"Java",LocalDate.of(1999,11,11));
            node2.addRelation(node,"Friends");
            network.addNode(node2);
            node = node2;
        }
        node.addRelation(node1,"FRIENDS");//a Cn graph

        Algorithms alg = new Algorithms(network);

        Assertions.assertEquals(false,alg.existsArticulationPoints());
    }

    @Test
    void Test3(){
        Network network = new Network();
        Algorithms alg = new Algorithms(network);

        Assertions.assertEquals(false,alg.existsArticulationPoints());
    }
}