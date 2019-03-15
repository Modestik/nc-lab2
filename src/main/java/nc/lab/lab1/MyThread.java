package nc.lab.lab1;

import lombok.Getter;
import lombok.Setter;

public class MyThread extends Thread {

    @Getter
    @Setter
    private int sum = 0;

    private int i;
    private int j;

    public MyThread(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public void run() {
        int[][] firstMatrix = Matrix.getFirstMatrix();
        int[][] secondMatrix = Matrix.getSecondMatrix();
        for (int k = 0; k < secondMatrix.length; k++)
            sum += firstMatrix[i][k] * secondMatrix[k][j];
        Matrix.getResultMatrix()[i][j] =sum;
    }
}