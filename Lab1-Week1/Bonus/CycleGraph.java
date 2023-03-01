package Bonus;

public class CycleGraph extends Bonus {

    public CycleGraph(String [] arg) {
        if(arg.length==0 || arg.length>=3) {
            System.out.println("The number of arguments is not valid, so by default the nr of vertices = 1");
            this.n = 1;
            this.adjacencyMatrix = new int[1][1];
        }
        else {
            int nrVertices = 0 ;
            try {
                nrVertices = Integer.parseInt(arg[0]);
            }
            catch ( NumberFormatException exp ){
                System.out.println("The first argument isn't an int so by default the nr of vertives = 1 ");
                this.n = 1;
                this.adjacencyMatrix = new int[1][1];
            }
            if( nrVertices < 1 ){
                System.out.println("The first argument needs to be positive, so by default the nr of vertives =1 ");
                this.n = 1;
                this.adjacencyMatrix = new int[1][1];
            }
            else {
                this.n = nrVertices;
                this.adjacencyMatrix = new int[nrVertices][nrVertices];
            }
        }
    }

    @Override
    public void createMatrix() {

        this.populateMatrixWith0();

        for (int i = 0; i < this.n - 1; ++i) {
            this.adjacencyMatrix[i][i + 1] = this.adjacencyMatrix[i + 1][i] = 1;// We create the cycle graph 1 - 2 - 3 - ... - n - 1
        }
        this.adjacencyMatrix[this.n - 1][0] = this.adjacencyMatrix[0][this.n - 1] = 1;
    }

    private void copyMatrixSecondInFirst(int [][] firstMatrix, int [][] secondMatrix){
        for( int i=0 ; i < this.n ; ++i){
            for (int j = 0 ; j< this.n ; ++j){
                firstMatrix[i][j] = secondMatrix[i][j];
            }
        }
    }
    private int multiplyAndAddLineWithColumn(int line, int[][] firstMatrix, int column, int[][] secondMatrix) {
        int sum = 0;
        for (int i = 0; i < this.n; ++i) {
            sum = sum + (firstMatrix[line][i] * secondMatrix[i][column]);
        }
        return sum;
    }

    private void multiplyOriginalMatrixWithGivenOne(int[][] givenMatrix) {
        int[][] givenMatrixAux = new int[this.n][this.n];
        this.copyMatrixSecondInFirst(givenMatrixAux,givenMatrix);
        for (int i = 0; i < this.n; ++i) {
            for (int j = 0; j < this.n; ++j) {
                givenMatrix[i][j] = this.multiplyAndAddLineWithColumn(i, this.adjacencyMatrix, j, givenMatrixAux);
            }
        }
    }

    public void printMatrixToThePowerOfN(int powerN) {
        int[][] finalResult = new int[this.n][this.n];
        this.copyMatrixSecondInFirst(finalResult, this.adjacencyMatrix);

        for (int i = 0; i < powerN - 1; i++) {
            multiplyOriginalMatrixWithGivenOne(finalResult);
        }
        System.out.println("The matrix to the power of " + powerN + " is :");
        for (int i = 0; i < this.n; ++i) {
            for (int j = 0; j < this.n; ++j) {
                System.out.print(finalResult[i][j]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    @Override
    public void printMatrix(){
        StringBuilder line = new StringBuilder(this.n);
        for( int i=0 ; i < this.n ; ++i){
            for( int j=0 ; j < this.n ; ++j){
                System.out.print(this.adjacencyMatrix[i][j]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }
}
