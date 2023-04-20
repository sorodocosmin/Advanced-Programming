package org.example.homework;

import org.example.compulsory.ExplorationMap;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TimeKeeper extends Thread {
    private long startTime;
    private boolean timeExpired;
    private long timeLimit;
    private boolean threadStarted = false;

    private ExplorationMap explorationMap;

    private BufferedWriter fileWriter;


    public TimeKeeper(ExplorationMap explorationMap){

        this.setDaemon(true);

        this.explorationMap = explorationMap;

        this.timeExpired = false;
        this.timeLimit = 60_000;//1 minute

        try {
            this.fileWriter = new BufferedWriter(new FileWriter("time.txt"));
        }
        catch (IOException e) {
            System.out.println("COULDN'T OPEN FILE");
            throw new RuntimeException(e);
        }
    }

    public boolean isThreadStarted() {
        return this.threadStarted;
    }

    @Override
    public void run(){
        this.threadStarted = true;
        this.startTime = System.currentTimeMillis();

        while(!this.timeExpired){
            double timeSinceStarted = System.currentTimeMillis() - this.startTime;
            try {
                this.fileWriter.write("Time since started: " + (timeSinceStarted/1000) + " s\n");
                this.fileWriter.flush();
                sleep(400);

                if(timeSinceStarted >= this.timeLimit){
                    this.timeExpired = true;
                    this.fileWriter.write("Time expired");
                    this.fileWriter.flush();
                }
                else if(this.explorationMap.finishedVisited()){
                    this.timeExpired = true;
                    this.fileWriter.write("Exploration finished");
                    this.fileWriter.flush();
                }
            }
            catch (IOException e) {
                System.out.println("Error at writing time");;
            }
            catch (InterruptedException e) {
                System.out.println("Error at sleeping");
            }

        }
    }

}
