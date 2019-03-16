package nc.lab.lab2;

import lombok.Getter;
import lombok.Setter;
import nc.lab.lab1.Matrix;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        //Считать размер маcссива
        Scanner scanner = new Scanner(System.in);
        int arrLength = scanner.nextInt();

        long startTime = System.currentTimeMillis();
        //Заполнить массив
        int[] arr = new int[arrLength];
        Random random = new Random();
        for (int i = 0; i < arrLength; i++) {
            arr[i] = random.nextInt(100);
        }
        System.out.println(toStringArr(arr));

        SheelSorting(arr);
        System.out.println(toStringArr(arr));

        long timeSpent = System.currentTimeMillis() - startTime;
        System.out.println("программа выполнялась " + timeSpent + " миллисекунд");
    }

    private static void SheelSorting(int[] arr) {
        int j;
        int step = arr.length / 2;
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService =
                Executors.newFixedThreadPool(cores);
        while (step > 0) {
            for (int i = 0; i < (arr.length - step); i++) {
                executorService.submit(new MyThread(step, i, arr));
            }
            step = step / 2;
        }
        executorService.shutdown();
    }

    private static String toStringArr(int[] arr) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("------------\n");
        for (int i = 0; i < arr.length; i++) {
            stringBuilder.append(arr[i] + "\t");
        }
        return stringBuilder.toString();
    }


}

