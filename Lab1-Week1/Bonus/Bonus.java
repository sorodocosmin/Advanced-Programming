package Bonus;

public abstract class Bonus {
    protected int [][] adjacencyMatrix;
    protected int n;

    protected void populateMatrixWith0 (){
        for (int i = 0; i < this.n; ++i) {
            for (int j = 0; j < this.n; ++j) {
                this.adjacencyMatrix[i][j] = 0;
            }
        }
    }
    public abstract void printMatrix();

    public abstract void createMatrix();
}
