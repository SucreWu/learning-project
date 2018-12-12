package com.wujie.learning.algorithm;

import java.util.HashMap;

/**
 * @Description: 散列表的使用场景
 * @Auther: wujie
 * @Date: 2018/12/11 15:03
 * @version: V1.0
 */
public class HashTable1 {

    // 封装一下三次方
    public static int pow(int i){
        return (int)Math.pow(i, 3);
    }

    /**
     * 一道面试题，给定方程a1x1^3 + a2x2^3 + a3x3^3 + a4x4^3 + a5x5^3 = 0，请写一个程序，从控制台输入
     * a1,a2,a3,a4,a5,然后求共有多少整数解，其中限定-50≤ x1,x2,x3,x4,x5 ＜50。
     * 解题思路：
     * 将方程左边的第四、五项移到右边：a1x1^3 + a2x2^3 + a3x3^3 = - (a4x4^3 + a5x5^3)
     * 利用等式两边相等，将枚举转换成查找，也就是将等式左边的所有计算结果放入散列表中（一种结果会对应多种情况），
     * 再根据等式右边的计算结果，在散列表中查找，找出计算结果为A的有几种情况，再进行累加即可
     */
    private static void search(int a, int b, int c, int d, int e){
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int i = -50; i < 50; i++){
            for (int j = -50; j < 50; j++){
                for (int k = -50; k < 50; k++){
                    int result = a * pow(i) + b * pow(j) + c * pow(k);
                    Integer v = hashMap.get(result);
                    if (v != null){
                        hashMap.put(result, v+1);
                    }else {
                        hashMap.put(result, 1);
                    }
                }
            }
        }
        int sum = 0;
        for (int i = -50; i < 50; i++) {
            for (int j = -50; j < 50; j++) {
                int result = a * pow(i) + b * pow(j);
                Integer v = hashMap.get(-result);
                if (v != null){
                    sum = sum + v;
                }
            }
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {
        HashTable1.search(1,2,3,4,5);
    }

}
