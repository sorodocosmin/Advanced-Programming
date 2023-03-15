package Compulsory;

import java.util.ArrayList;
import java.util.List;

public class Main1 {
    static public void main(String [] args){
        List<Node1> nodeList = new ArrayList<>();
        Person1 person1 = new Programmer1("Cosmin","Java");
        Person1 person2 = new Designer1("Mihai","red");
        Person1 person3 = new Designer1("Ion","blue");
        Company1 company1 = new Company1("ABA",5);

        nodeList.add(person1);
        nodeList.add(person2);
        nodeList.add(person3);
        nodeList.add(company1);


        System.out.println("The network is : " + nodeList);
    }
}
