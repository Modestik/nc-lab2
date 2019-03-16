package nc.lab.lab2;

import lombok.Getter;
import lombok.Setter;

public class MyThread extends Thread {

    @Getter
    @Setter
    private int sum = 0;

    private int step;
    private int j;
    int[] arr;

    public MyThread(int step, int j, int[] arr) {
        this.step = step;
        this.j = j;
        this.arr = arr;
    }

    @Override
    public void run() {
        while ((j >= 0) && (arr[j] > arr[j + step])) {
            swap(arr, j, j + step);
            j = j - step;
        }
    }

    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
