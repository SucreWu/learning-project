package com.wujie.learning.thread;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 安全终止线程
 * @Auther: wujie
 * @Date: 2019/10/23 15:10
 * @version: V1.0
 */
public class Shutdown {
    public static void main(String[] args) throws InterruptedException{
        Runner one = new Runner();
        Thread countThread = new Thread(one);
        countThread.start();
        // 睡眠1秒，main线程对countThread进行中断，使countThread能够感知中断而结束
        TimeUnit.SECONDS.sleep(1);
        countThread.interrupt();
        Runner two = new Runner();
        countThread = new Thread(two);
        countThread.start();
        // 睡眠1秒，main线程对Runner two进行取消，使countThread线程能够感知到自定义标识字段on修改为false
        TimeUnit.SECONDS.sleep(1);
        two.cancel();

    }

    private static class Runner implements Runnable{
        private long i;
        private volatile boolean on = true;
        @Override
        public void run() {
            while (on & !Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println("Count i = " + i);
        }

        public void cancel(){
            on = false;
        }
    }
}
