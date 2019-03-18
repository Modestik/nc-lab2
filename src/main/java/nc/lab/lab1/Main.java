package nc.lab.lab1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Считать размеры матриц
        Scanner scanner = new Scanner(System.in);
        int rowFirstMatrix = scanner.nextInt();
        int colFirstMatrix = scanner.nextInt();
        int colSecondMatrix = scanner.nextInt();

        Matrix matrix = new Matrix(rowFirstMatrix, colFirstMatrix, colSecondMatrix);

        System.out.println(Matrix.toStringMatrix(matrix.getFirstMatrix()));
        System.out.println(Matrix.toStringMatrix(matrix.getSecondMatrix()));

        //Результирующая матрица.
        matrix.multiplyMatrix();
        System.out.println(Matrix.toStringMatrix(matrix.getResultMatrix()));

    }
}

