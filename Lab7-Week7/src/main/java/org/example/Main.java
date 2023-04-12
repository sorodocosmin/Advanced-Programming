package org.example;

import org.example.compulsory.Exploration;
import org.example.compulsory.Robot;

public class Main {
    public static void main(String[] args) {

        var explore = new Exploration(5);
        explore.addRobot(new Robot("Wall-E"));
        explore.addRobot(new Robot("R2D2"));
        explore.addRobot(new Robot("Optimus Prime"));

        explore.start();






        // Homework : - un DFS in care tinem minte celulele in care am fost
        // Bonus : - implementarea algoritmilor in maniera concurenta, pentru un API de grafuri ci nu concret, pt problema noastra :) ;
    }
}