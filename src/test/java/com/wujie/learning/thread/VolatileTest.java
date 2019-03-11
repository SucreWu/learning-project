package com.wujie.learning.thread;

import java.util.AbstractQueue;

/**
 * @Description:
 * @Auther: wujie
 * @Date: 2019/2/27 16:59
 * @version: V1.0
 */
public class VolatileTest {

    int i = 0;
    volatile boolean flag = false;

    //Thread A
    public void write(){
        i = 2;              //1
        flag = true;        //2
    }

    //Thread B
    public void read(){
        if(flag){                                   //3
            System.out.println("---i = " + i);      //4
        }
    }

    public static void main(String[] args) {
        VolatileTest test = new VolatileTest();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.write();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.read();
            }
        });
        t1.start();
        t2.start();
        // 因为volatile会禁止指令重排，所以A线程在写volatile变量之前所有可见的共享变量，
        // 在线程B读同一个volatile变量后，将立即变得对线程B可见。

    }
}