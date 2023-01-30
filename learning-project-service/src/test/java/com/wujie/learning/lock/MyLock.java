package com.wujie.learning.lock;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

public class MyLock implements Lock {

    // 原子操作类，记录线程
    AtomicReference<Thread> owner = new AtomicReference<>();

    // 等待线程队列（存放CAS失败的线程）
    LinkedBlockingQueue<Thread> list = new LinkedBlockingQueue<>();

    @Override
    public void lock() {
        // 每次等待线程被唤醒时，都会重新尝试CAS操作，当且仅当竞争成功后，才会继续执行lock()方法之后的代码，否则就无限CAS
        while (!owner.compareAndSet(null, Thread.currentThread())){
            list.add(Thread.currentThread());
            LockSupport.park(); // 阻塞当前线程
            list.remove(Thread.currentThread()); // 线程被唤醒，需要删除防止内存泄漏
        }
    }

    @Override
    public void unlock() {
        if (owner.compareAndSet(Thread.currentThread(), null)){
            // 释放所有阻塞线程
            for (Object object : list.toArray()){
                Thread next = (Thread)object;
                LockSupport.unpark(next);
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
