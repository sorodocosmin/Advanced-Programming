package org.example.homework.menu;

import org.example.compulsory.Exploration;
import org.example.compulsory.Robot;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuMain implements Menu {

    final private List<Integer> identifierOptions;
    final private List<String> textOptions;
    private int currentOption;
    private final Exploration exploration;

    private boolean exit = false;

    final private MenuStartExploration menuStartExploration ;
    final private MenuStopExploration menuStopExploration;

    public MenuMain(int sizeMatrix){

        this.identifierOptions = new ArrayList<>();
        this.textOptions = new ArrayList<>();

        this.identifierOptions.add(1);
        this.identifierOptions.add(2);
        this.identifierOptions.add(3);
        this.identifierOptions.add(4);

        this.textOptions.add("Add new Robot");
        this.textOptions.add("Start Exploration");
        this.textOptions.add("Stop Exploration");
        this.textOptions.add("Exit");

        this.exploration = new Exploration(sizeMatrix);

        this.menuStartExploration = new MenuStartExploration(this.exploration);
        this.menuStopExploration = new MenuStopExploration(this.exploration);
    }

    public boolean isExit() {
        return exit;
    }
    @Override
    public void printMenu(){

        System.out.println("Please introduce ONLY THE NUMBER corresponding to the option that you want to select");

       for( int i=0 , n=this.identifierOptions.size(); i < n ; ++i ){
           System.out.println(this.identifierOptions.get(i) + ". " + this.textOptions.get(i));
       }

        this.readOption();
    }
    @Override
    public void handleOption(){
        if(this.currentOption == this.identifierOptions.get(0)) {//Add new Robot Option
            this.handleAddNewRobotOption();
        }
        else if ( this.currentOption == this.identifierOptions.get(1)){//Start Exploration
            this.handleStartExploration();
        }
        else if ( this.currentOption == this.identifierOptions.get(2)){//Stop Exploration
            this.handleStopExploration();
        }
        else if ( this.currentOption == this.identifierOptions.get(3)){//Exit
            this.handleExit();
        }
    }

    private void readOption(){

        Scanner scanner = new Scanner(System.in);

        try {

            this.currentOption = scanner.nextInt();//throws exception if cannot be converted to an int

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

    private void handleAddNewRobotOption(){

        this.exploration.addRobot(new Robot(this.readNameRobot()));
        System.out.println("Robot added successfully");


    }
    private void handleStartExploration(){
        this.menuStartExploration.printMenu();
        this.menuStartExploration.handleOption();
    }
    private void handleStopExploration(){
        this.menuStopExploration.printMenu();
        this.menuStopExploration.handleOption();
    }

    private void handleExit(){

        this.exit = true;
        System.exit(0);

    }

    private String readNameRobot(){
        System.out.println("Introduce the name of the robot that you want to add : ");
        Scanner scanner = new Scanner(System.in);
        String robotName = scanner.nextLine();

        return robotName;
    }



}
