package com.wujie.learning.thread;

import sun.jvm.hotspot.memory.StringTable;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Auther: wujie
 * @Date: 2019/10/30 11:01
 * @version: V1.0
 */
public class ThreadTest{
    private static boolean ready;
    private static int number;
    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while(!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }
    public static void main(String[] args) throws InterruptedException{
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                LockSupport.park();
                System.out.println(111111);
            }
        });
        t.start();
        Thread.sleep(3000);
        LockSupport.unpark(t);
    }
}
