package Bonus;

public class Main {
    public static void main(String [] args){

        Bonus example1 = new CycleGraph(args);
        example1.createMatrix();
        example1.printMatrix();
        ((CycleGraph) example1).printMatrixToThePowerOfN(2);

        Bonus example2 = new RegularGraph(args);
        example2.createMatrix();
        example2.printMatrix();
        ((RegularGraph)example2).printIfTheCreatedMatrixIsCorrect();
    }
}
