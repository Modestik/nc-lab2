package nc.lab.lab1;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Matrix {

    @Getter
    @Setter
    private int[][] firstMatrix;
    @Getter
    @Setter
    private int[][] secondMatrix;
    @Getter
    private int[][] resultMatrix;

    public Matrix(int rowFirstMatrix, int colFirstMatrix, int colSecondMatrix) {
        this.firstMatrix = new int[rowFirstMatrix][colFirstMatrix];
        this.secondMatrix = new int[colFirstMatrix][colSecondMatrix];
        randomMatrix(firstMatrix);
        randomMatrix(secondMatrix);
    }


    private void randomMatrix(int[][] matrix) {
        Random random = new Random();
        for (int row = 0; row < matrix.length; ++row)           // Цикл по строкам матрицы.
            for (int col = 0; col < matrix[row].length; ++col)  // Цикл по столбцам матрицы.
                matrix[row][col] = random.nextInt(10);         // Случайное число от 0 до 10.
    }


    public int[][] multiplyMatrix() {
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService =
                Executors.newFixedThreadPool(cores);

        resultMatrix = new int[firstMatrix.length][secondMatrix[0].length];
        for (int row = 0; row < firstMatrix.length; row++) {  // Цикл по строкам матрицы.
            for (int col = 0; col < secondMatrix[0].length; col++) {  // Цикл по столбцам матрицы.
                executorService.submit(new MyThread(row, col, firstMatrix, secondMatrix, resultMatrix));
            }
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resultMatrix;
    }

    public static String toStringMatrix(int[][] matrix) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("------------\n");
        for (int row = 0; row < matrix.length; ++row) {
            for (int col = 0; col < matrix[row].length; ++col) {
                stringBuilder.append(matrix[row][col] + "\t");
            }
            stringBuilder.append("\n");
        }
        stringBuilder.append("------------\n");
        return stringBuilder.toString();
    }
}