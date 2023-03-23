package org.example.Bonus;

import org.example.Homework.Problem;
import org.example.Homework.ProblemGenerator;
import org.junit.jupiter.api.Test;

import javax.sound.midi.Soundbank;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmTest {

    @Test
    public void test1 (){
        Problem problem = ProblemGenerator.random();

        long initialTime, runningTime;

        initialTime = System.currentTimeMillis();
        Algorithm algGreedy = new GreedyAlgorithm(problem);
        algGreedy.printMaximMatching();
        runningTime = System.currentTimeMillis() - initialTime;
        System.out.println("Time : " + runningTime + " milliseconds ;\n");

        initialTime = System.currentTimeMillis();
        Algorithm algGraph4j = new Graph4jAlgorithm(problem);
        algGraph4j.printMaximMatching();
        runningTime = System.currentTimeMillis() - initialTime;
        System.out.println("Time : " + runningTime + " milliseconds ;\n");

        initialTime = System.currentTimeMillis();
        Algorithm jGraphTAlgorithm = new JGraphTAlgorithm(problem);
        jGraphTAlgorithm.printMaximMatching();
        runningTime = System.currentTimeMillis() - initialTime;
        System.out.println("Time : " + runningTime + " milliseconds ;\n");

    }

    @Test
    public void test2(){
        test1();
    }

    @Test
    public void test3(){
        test1();
    }



}