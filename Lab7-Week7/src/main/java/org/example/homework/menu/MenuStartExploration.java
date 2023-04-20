package org.example.homework.menu;

import org.example.compulsory.Exploration;
import org.example.compulsory.Robot;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuStartExploration implements Menu {
    final private  List<Integer> identifierOptions;
    final private  List<String> textOptions;

    final private Exploration exploration;

    private int currentOption;

    public MenuStartExploration(Exploration exploration){

        this.identifierOptions = new ArrayList<>();
        this.textOptions = new ArrayList<>();

        this.identifierOptions.add(1);
        this.identifierOptions.add(2);

        this.textOptions.add("Start Exploration for all Robots");
        this.textOptions.add("Start Exploration for a specific Robot");


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
            this.handleStartAllRobots();
        }
        else if ( this.currentOption == this.identifierOptions.get(1)){//Start Exploration for a specific robot
            this.handleStartSpecificRobot();
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

    private void handleStartAllRobots(){

        this.exploration.start();

    }

    private void handleStartSpecificRobot(){
        System.out.println("Introduce the exact same name that you entered when adding the robot");

        Scanner scanner = new Scanner(System.in);

        this.exploration.start(scanner.nextLine());

    }


}
