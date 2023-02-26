package Homework;

public class Homework {
    private int n;
    private int [][] matrix;

    private boolean inputIsValid(String [] number){
        if(number.length != 1)
            return false;
        int n ;
        try{
            n = Integer.parseInt(number[0]);
        }
        catch (NumberFormatException e) {
            return false;
        }

        return n >= 1;
    }

    private void createMatrix(){
        this.matrix = new int [this.n][this.n];

        int k ;

        for( int i = 0; i < this.n ; ++i){
            k=i;
            for( int j = 0; j < this.n ; ++j){
                this.matrix[i][j] = (k + 1)%this.n;
                if((k+1)%this.n == 0){
                    this.matrix[i][j] = n;
                }
                k++;
            }
        }
    }
    private StringBuilder stringConcatenationOfLineI( int i){
        StringBuilder lineI= new StringBuilder(n);
        for(int j = 0 ; j < this.n ; ++j){
            lineI.append(this.matrix[i][j]);
            lineI.append(" ");
        }
        return lineI;
    }
    private void printMatrix(boolean simulatePrinting){
        for( int i = 0 ; i < this.n ; ++i){
            if (!simulatePrinting){
                System.out.println(stringConcatenationOfLineI(i));
            }
        }
    }
    public void doTheHomework(String [] number){

        long startTime,stopTime;
        startTime = System.currentTimeMillis();

        if (!this.inputIsValid(number)){
            System.out.println("The input is not valid!");
            return;
        }
        else{
            this.n= Integer.parseInt(number[0]);
        }


        this.createMatrix();

        this.printMatrix(n > 100);

        stopTime = System.currentTimeMillis();
        System.out.println("The running time of the application was " + (stopTime - startTime) + " milliseconds");

    }
}
