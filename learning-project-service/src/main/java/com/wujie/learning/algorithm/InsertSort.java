package com.wujie.learning.algorithm;

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

    public static void main(String[] args) {
        int[] a = {3,6,7,2,1,4,10,22,5};
        insertSort(a);
        for(int i : a){
            System.out.println(i);
        }
    }
}
