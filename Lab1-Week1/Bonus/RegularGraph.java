package Bonus;

import java.util.ArrayList;
import java.util.Arrays;

public class RegularGraph extends Bonus{
    private final int vertexDegree;
    boolean itExists = true;
    public RegularGraph ( String [] arg){
        if ( arg.length != 2){
            System.out.println("The number of arguments is not valid, so by default the nr of vertices = 1, and the vertex degree = 0");
            this.n  = 1 ;
            this.vertexDegree = 0;
            this.adjacencyMatrix = new int [1][1];
        }
        else{
            int nrVertices, vertexDegree ;

            try {
                nrVertices = Integer.parseInt(arg[0]);
            }
            catch (NumberFormatException exp){
                System.out.println("The first argument isn't an int so by default the nr of vertives = 1, and the vertex degree = 0");
                this.n  = 1 ;
                this.vertexDegree = 0;
                this.adjacencyMatrix = new int [1][1];
                return;
            }

            try {
                vertexDegree = Integer.parseInt(arg[1]);
            }
            catch (NumberFormatException exp){
                System.out.println("The second argument isn't an int so by default the nr of vertives = 1, and the vertex degree = 0");
                this.n  = 1 ;
                this.vertexDegree = 0;
                this.adjacencyMatrix = new int [1][1];
                return;
            }

            if ( (vertexDegree < 0 || nrVertices < 1) || vertexDegree >= nrVertices ){
                System.out.println("Nr of vertices needs to be > 1 AND the degree needs to be > 0 AND degree < nrVertices \nas this wasn't respected, by default the nr of vertives = 1, and the vertex degree = 0 ");
                this.n  = 1 ;
                this.vertexDegree = 0;
                this.adjacencyMatrix = new int [1][1];
            }
            else{
                this.n = nrVertices;
                this.vertexDegree = vertexDegree;
                this.adjacencyMatrix = new int [nrVertices][nrVertices];
            }

        }
    }

    @Override
    public  void createMatrix() {
        if ((this.n * this.vertexDegree) % 2 == 1) {
            System.out.println("The sum of the degrees of all nodes needs to be even\n Therefore it doesn't exist a matrix with " + this.n + " vertices " + "and degree " + this.vertexDegree);
            this.itExists = false;
            return;
        }

        this.populateMatrixWith0();

        for (int i = 0; i < this.n; ++i) {//considering all nodes put in a circle, node i will have as neighbours the closest k nodes
            for (int k = 1; k <= ( (this.vertexDegree%2 == 1) ? (this.vertexDegree - 1) / 2 : this.vertexDegree/2); ++k) {//every i node will have i+1 .. i+k/2 and i-1 .. i-k/2 as neighbours
                this.adjacencyMatrix[i][Math.floorMod(i + k, this.n)] = this.adjacencyMatrix[Math.floorMod(i + k, this.n)][i] = 1;
                this.adjacencyMatrix[i][Math.floorMod(i - k, this.n)] = this.adjacencyMatrix[Math.floorMod(i - k, this.n)][i] = 1;
            }
            if (this.vertexDegree % 2 == 1){//the k_th neighbour will be the opposite node
                this.adjacencyMatrix[i][Math.floorMod(i + this.n / 2,this.n)] = 1;
            }
        }
    }

    @Override public void printMatrix(){
        if ( this.itExists == true ) {
            StringBuilder line = new StringBuilder(this.n);
            for (int i = 0; i < this.n; ++i) {
                for (int j = 0; j < this.n; ++j) {
                    System.out.print(this.adjacencyMatrix[i][j]);
                    System.out.print(" ");
                }
                System.out.print("\n");
            }
        }
        else{
            System.out.println("It doesn't exist such matrix");
        }
    }

    public void printIfTheCreatedMatrixIsCorrect(){
        //it needs to be symmetrically and every node needs to have the specified degree
        if( this.itExists == true){
            for (int i=0 ; i<this.n ; i ++){
                int degree = 0;
                for( int j = 0 ; j <this.n ; j ++){
                    degree+= this.adjacencyMatrix[i][j];
                    if ( adjacencyMatrix[i][j]!=adjacencyMatrix[j][i]){
                        System.out.println("Matrix Wrong ! - is not symmetrically");
                        return;
                    }
                }
                if ( degree != this.vertexDegree ){
                    System.out.println("Matrix Wrong ! - node "+ i +" has a different degree");
                    return;
                }
            }
            System.out.println("The matrix is correct ! ");
        }
    }
}
