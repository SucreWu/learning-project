package com.wujie.learning.thread;

import com.wujie.learning.util.SleepUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Auther: wujie
 * @Date: 2019/10/23 17:18
 * @version: V1.0
 */
public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException{
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();
    }

    static class Wait implements Runnable{
        @Override
        public void run() {
            synchronized (lock){
                // 不满足条件就释放锁进入WAITING状态
                while (flag){
                    System.out.println(Thread.currentThread() + "flag is true.wait@" +
                    new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread() + "flag is false.wait@" +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable{
        @Override
        public void run() {
            synchronized (lock){
                // 获取lock的锁，然后进行通知，通知时不会释放lock的锁
                // 直到当前线程释放了lock后，WaitThread才能从wait方法中返回
                System.out.println(Thread.currentThread() + " hold lock. notify @ " +
                        new SimpleDateFormat("YY:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                SleepUtils.second(5);
            }
//            // 这边等待时间长一点wait线程竞争到锁资源后就会先执行
//            SleepUtils.second(5);
            // 再次加锁
            synchronized (lock){
                System.out.println(Thread.currentThread() + " hold lock. again @ " +
                        new SimpleDateFormat("YY:mm:ss").format(new Date()));
                SleepUtils.second(5);
            }
        }
    }
}
