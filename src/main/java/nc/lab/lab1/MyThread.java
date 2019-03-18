package nc.lab.lab1;

import lombok.Getter;
import lombok.Setter;

public class MyThread extends Thread {

/*    @Getter
    @Setter
    private int sum = 0;*/

    private int i;
    private int j;
    int[][] firstMatrix;
    int[][] secondMatrix;
    int[][] resultMatrix;

    public MyThread(int i, int j, int[][] firstMatrix, int[][] secondMatrix, int[][] resultMatrix) {
        this.i = i;
        this.j = j;
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.resultMatrix = resultMatrix;
    }

    @Override
    public void run() {
        int sum = 0;
        for (int k = 0; k < secondMatrix.length; k++){
            sum += firstMatrix[i][k] * secondMatrix[k][j];
        resultMatrix[i][j] = sum;
        }
    }
}