package nc.lab.lab2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        //Считать размер маcссива
        Scanner scanner = new Scanner(System.in);
        int arrLength = scanner.nextInt();

        Array array = new Array();
        array.setArr(Array.randomArr(new int[arrLength]));
        System.out.println(array.toString());
        array.sheelSorting();
        System.out.println(array.toString());
    }
}

