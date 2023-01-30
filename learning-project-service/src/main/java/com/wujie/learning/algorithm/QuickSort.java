package com.wujie.learning.algorithm;
public  class QuickSort {

    public static void quickSort(int[] arr, int low, int high) {
        int i, j, temp, t;
        i = low;
        j = high;
        if (low > high) {
            return;
        }
        temp = arr[low];
        while (temp < arr[j] && i < j) {
            j--;
        }
        while (temp > arr[i] && i < j) {
            i++;
        }
        if (i < j) {
            t = arr[j];
            arr[j] = arr[i];
            arr[i] = t;
        }

        arr[low] = arr[i];
        arr[i] = temp;
        quickSort(arr, low, j - 1);
        quickSort(arr, j + 1, high);
    }
}
