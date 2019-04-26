package com.javarush.task;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int [] array = {7, 5, 8, 3, 1, 0};
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void bubbleSort(int [] arr) {
        boolean inter = true;
        while (inter) {
            inter = false;
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    inter = true;
                }
            }
        }
    }
}
