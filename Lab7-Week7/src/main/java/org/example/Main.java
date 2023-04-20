package org.example;

import org.example.compulsory.Exploration;
import org.example.compulsory.Robot;
import org.example.homework.menu.MenuMain;

public class Main {
    public static void main(String[] args) {

//        Robot robot1 = new Robot("Wall-E");
//        Robot robot2 = new Robot("R2D2");
//        Robot robot3 = new Robot("Optimus Prime");
//        var explore = new Exploration(5);
//        explore.addRobot(robot1);
//        explore.addRobot(robot2);
//        explore.addRobot(robot3);
//
//        explore.start(new Robot("Wall-E"));
//        explore.start(new Robot("R2D2"));
//
//        Scanner keyboard = new Scanner(System.in);
//        System.out.println("enter an integer");
//        int myint = keyboard.nextInt();
//        if(myint == 1){
//            explore.stop(robot2);
//        }

        MenuMain menuMain = new MenuMain(10);

        while ( !menuMain.isExit() ){

            menuMain.printMenu();
            menuMain.handleOption();

        }






        // Homework : - un DFS in care tinem minte celulele in care am fost
        // Bonus : - implementarea algoritmilor in maniera concurenta, pentru un API de grafuri ci nu concret, pt problema noastra :) ;
    }
}