package com.wujie.learning.thread;

import com.wujie.learning.util.SleepUtils;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 线程中断
 * @Auther: wujie
 * @Date: 2019/10/23 10:30
 * @version: V1.0
 */
public class Interrupted {
    public static void main(String[] args) throws InterruptedException{
        // sleepThread不停的尝试睡眠
        Thread sleepThread = new Thread(new SleepRunner());
        // busyThread不停的运行
        Thread busyThread = new Thread(new BusyRunner());
//        // 通知线程中断，线程并不会停止运行，这里设置成守护线程只是方便程序停止运行。
//        sleepThread.setDaemon(true);
//        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();
        TimeUnit.SECONDS.sleep(5);
        sleepThread.interrupt();
        busyThread.interrupt();
        System.out.println("SleepThread interrupter is " + sleepThread.isInterrupted());
        System.out.println("BusyThread interrupter is " + busyThread.isInterrupted());
        // 防止sleepThread和busyThread立刻退出
        SleepUtils.second(2);
    }

    static class SleepRunner implements Runnable{
        @Override
        public void run() {
            while (true){
                SleepUtils.second(10);
            }
        }
    }

    static class BusyRunner implements Runnable{
        @Override
        public void run() {
            while (true){

            }
        }
    }
}
