package com.wujie.learning.thread;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Auther: wujie
 * @Date: 2019/10/23 18:16
 * @version: V1.0
 */
public class Join {

    public static void main(String[] args) throws InterruptedException{
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++ ){
            // 每个线程的thread成员变量都指向前一个线程的引用
            // 当调用join方法时，需要等待前一个线程终止，才能从等待中返回
            Thread thread = new Thread(new Domino(previous), String.valueOf(i));
            thread.start();
            previous = thread;
        }
        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName() + " terminate.");
    }

    static class Domino implements Runnable{
        private Thread thread;
        public Domino(Thread thread){
            this.thread = thread;
        }
        @Override
        public void run() {
            try {
                thread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " terminate.");
        }
    }
}
