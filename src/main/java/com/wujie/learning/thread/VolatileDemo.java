package com.wujie.learning.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Auther: wujie
 * @Date: 2019/10/16 15:18
 * @version: V1.0
 */
public class VolatileDemo {
    public static void main(String[] args) throws InterruptedException{
        A a = new A();
        Long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a.changeNum();
            }
        });
        thread.start();
        thread.interrupt();

        while (a.i == 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("循环结束,耗时:"
                + (System.currentTimeMillis() - start) + ",i的值为: " + a.i);
    }
}





class A {
    int i = 0;

    public void changeNum() {
        i = 100;
    }
}