package com.wujie.learning.algorithm;

import java.util.PriorityQueue;

/**
 * @Description: 选择排序
 * 每次寻找出最小值放在序列的最前端。
 * @Auther: wujie
 * @Date: 2018/12/18 11:47
 * @version: V1.0
 */
public class ChooseSort {

    static void chooseSort(int[] array){
        int min, index = 0;
        for (int i = 0; i < array.length; i++){
            min = array[i];
            for (int j = i+1; j < array.length; j++){
                if (min > array[j]){
                    // 记录最小值和下标
                    min = array[j];
                    index = j;
                }
            }
            // 这里不用下标作为判断条件是因为上面循环内的if语句如果没触发，index的值会是上一次循环的值
            if (min != array[i]){
                array[index] = array[i];
                array[i] = min;
            }
        }
    }

    /**
     * 据说能用优先级队列
     * @param array 必须是个int数组
     * @return queue
     */
    static void chooseSort2(int[] array){
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i : array){
            queue.offer(i);
        }
        int i = 0;
        /*
        直接取是不会有排序效果的
        for(int j :queue){
            array[i++] = j;
        }
        */
        // 需要使用poll方法
        while (!queue.isEmpty()){
            array[i++] = queue.poll();
        }
    }

    public static void main(String[] args) {
        int[] a = {3,6,7,2,1,4,10,22,5};
        chooseSort(a);
        for(int i : a){
            System.out.println(i);
        }
        //-------------------------------我是分割线--------------------------------
        int[] a2 = {3,6,7,2,1,4,10,22,5};
        chooseSort2(a2);
        for(int i : a2){
            System.out.println(i);
        }
    }
}
