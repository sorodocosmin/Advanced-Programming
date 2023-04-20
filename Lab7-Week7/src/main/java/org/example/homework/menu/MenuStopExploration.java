package org.example.homework.menu;

import org.example.compulsory.Exploration;
import org.example.compulsory.Robot;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuStopExploration implements Menu {
    final private List<Integer> identifierOptions;
    final private  List<String> textOptions;

    final private Exploration exploration;

    private int currentOption;

    public MenuStopExploration(Exploration exploration){

        this.identifierOptions = new ArrayList<>();
        this.textOptions = new ArrayList<>();

        this.identifierOptions.add(1);
        this.identifierOptions.add(2);
        this.identifierOptions.add(3);

        this.textOptions.add("Stop Exploration for all Robots");
        this.textOptions.add("Stop Exploration for a specific Robot");
        this.textOptions.add("Stop Exploration for a specific Robot and for a specific time (in milliseconds)");


        this.exploration = exploration;

    }

    @Override
    public void printMenu() {

        System.out.println("Please introduce ONLY THE NUMBER corresponding to the option that you want to select");

        for( int i=0 , n=this.identifierOptions.size(); i < n ; ++i ){
            System.out.println(this.identifierOptions.get(i) + ". " + this.textOptions.get(i));
        }

        this.readOption();
    }

    @Override
    public void handleOption(){
        if(this.currentOption == this.identifierOptions.get(0)) {//Start Exploration for all Robots
            this.handleStopAllRobots();
        }
        else if ( this.currentOption == this.identifierOptions.get(1)){//Start Exploration for a specific robot
            this.handleStopSpecificRobot();
        }
        else if (this.currentOption == this.identifierOptions.get(2)) {
            this.handleStopSpecificRobotForSpecificTime();
        }
    }

    private void readOption(){

        Scanner scanner = new Scanner(System.in);

        try {

            this.currentOption = scanner.nextInt();//throws exception if it cannot be converted to an int

            if( this.identifierOptions.indexOf(this.currentOption)==-1){
                System.out.println("¡ Invalid Option !");
                this.readOption();
            }
        }
        catch (InputMismatchException e){
            System.out.println("¡ Please introduce only the NUMBER !");
            this.readOption();
        }

    }

    private void handleStopAllRobots(){

        this.exploration.stop();

    }

    private void handleStopSpecificRobot(){
        System.out.println("Introduce the exact same name that you entered when adding the robot");

        Scanner scanner = new Scanner(System.in);

        this.exploration.stop(scanner.nextLine());

    }

    private void handleStopSpecificRobotForSpecificTime(){
        System.out.println("Introduce the exact same name that you entered when adding the robot");

        Scanner scanner = new Scanner(System.in);
        String robotName = scanner.nextLine();


        try {
            System.out.println("Introduce only the number of milliseconds that you want the robot to be stopped");
            int milliseconds = scanner.nextInt();//throws exception if it cannot be converted to an int
            this.exploration.stop(robotName, milliseconds);
        }
        catch (InputMismatchException e){
            System.out.println("¡ The text that you entered cannot be converted to an integer !");
            this.handleStopSpecificRobotForSpecificTime();
        }
    }
}
