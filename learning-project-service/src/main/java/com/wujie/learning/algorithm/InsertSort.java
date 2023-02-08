package com.wujie.learning.algorithm;

import java.util.Arrays;

/**
 * @Description: 插入排序
 * 每步将一个待排序的纪录，按其关键码值的大小插入前面已经排序的文件中适当位置上，直到全部插入完为止。
 * @Auther: wujie
 * @Date: 2018/12/17 18:10
 * @version: V1.0
 */
public class InsertSort {
    static void insertSort(int[] array){
        // 从1开始是因为数组的第一个元素被当做初始的有序数组
        for (int i = 1; i < array.length; i++){
            // 待插入数值
            int temp = array[i];
            int j = i-1;
            // 满足条件则表示寻找到插入位置的下标
            for (; j >= 0 && temp < array[j]; j--){
                // 将当前下标的数向右移动一位，同时继续向左查找
                array[j+1] = array[j];
            }
            // j+1在j子减后就是插入位置的下标
            array[j+1] = temp;
        }
    }

    // 从后往前insert，将较大的值放在数组尾部
    public static void insertSort2(int[] array) {
        for (int i = array.length - 2; i >= 0; i--) {
            int temp = array[i];
            int j = i + 1;
            int insertIndex = i;
            for (; j < array.length; j++) {
                if (temp > array[j]) {
                    array[j - 1] = array[j];
                    insertIndex = j;
                }
            }
            array[insertIndex] = temp;
        }
    }

    // 2的优化版，第二次循环的控制变量增加判断条件，当比大小中止时，j就是需要插入的位置
    public static void insertSort3(int[] array) {
        for (int i = array.length - 2; i >= 0; i--) {
            int temp = array[i];
            int j = i + 1;
            for (; j < array.length && temp > array[j]; j++) {
                array[j - 1] = array[j];
            }
            array[j - 1] = temp;
        }
    }

    public static void main(String[] args) {
        int[] a = {3,6,7,2,1,4,22,10,5};
        insertSort(a);
        System.out.println(Arrays.toString(a));
        int[] b = {3,6,7,2,1,4,22,10,5};
        insertSort2(b);
        System.out.println(Arrays.toString(b));
    }
}
