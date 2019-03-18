package nc.lab.lab2;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Array {
    @Getter
    @Setter
    int[] arr;

    public Array() {
    }

    public static int[] randomArr(int[] arr) {
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }
        return arr;
    }

    public void sheelSorting() {
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
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("------------\n");
        for (int i = 0; i < arr.length; i++) {
            stringBuilder.append(arr[i] + "\t");
        }
        return stringBuilder.toString();
    }
}
