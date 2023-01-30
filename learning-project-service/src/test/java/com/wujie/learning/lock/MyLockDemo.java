package com.wujie.learning.lock;

import java.util.concurrent.CountDownLatch;

public class MyLockDemo {

    MyLock lock = new MyLock();
    int i = 0;

    public void safeIncrease(){
        for (int j = 0; j < 10000; j++){
            lock.lock();
            i++;
            lock.unlock();
        }
    }

    public void unsafeIncrease(){
        for (int j = 0; j < 10000; j++){
            i++;
        }
    }

    public static void main(String[] args) throws InterruptedException{
        MyLockDemo demo = new MyLockDemo();
        CountDownLatch count = new CountDownLatch(10);
        for (int i = 0; i < 10; i++){
            Thread t = new Thread(() -> {
//                demo.unsafeIncrease(); // 执行结果必然小于100000
                demo.safeIncrease(); // 执行结果等于100000
                count.countDown();
            });
            t.start();
        }
        count.await();
        System.out.println(demo.i);
    }
}
