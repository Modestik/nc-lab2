package nc.lab.lab1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Считать размеры матриц
        Scanner scanner = new Scanner(System.in);
        int rowFirstMatrix = scanner.nextInt();
        int colFirstMatrix = scanner.nextInt();
        int colSecondMatrix = scanner.nextInt();

        long startTime = System.currentTimeMillis();

        //Первая (левая) матрица.
        int[][] firstMatrix = new int[rowFirstMatrix][colFirstMatrix];
        //Вторая (правая) матрица.
        int[][] secondMatrix = new int[colFirstMatrix][colSecondMatrix];

        //Заполнение матриц
        Matrix.randomMatrix(firstMatrix);
        System.out.println(Matrix.toStringMatrix(firstMatrix));
        Matrix.randomMatrix(secondMatrix);
        System.out.println(Matrix.toStringMatrix(secondMatrix));

        //Добавление как ресурса
        Matrix.setFirstMatrix(firstMatrix);
        Matrix.setSecondMatrix(secondMatrix);

        //Результирующая матрица.
        int[][] resultMatrix = Matrix.multiplyMatrix();
        System.out.println(Matrix.toStringMatrix(resultMatrix));

        long timeSpent = System.currentTimeMillis() - startTime;
        System.out.println("программа выполнялась " + timeSpent + " миллисекунд");
    }
}

