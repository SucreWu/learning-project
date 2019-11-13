package com.wujie.learning.thread;

import javafx.concurrent.Worker;

import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 可重入锁
 * @Auther: wujie
 * @Date: 2019/10/21 10:33
 * @version: V1.0
 */
public class ReentrantLockTest {

    int a = 0;
    ReentrantLock lock = new ReentrantLock();

    public void writer(){
        lock.lock();
        try{
            a++;
        }finally {
            lock.unlock();
        }
    }

    public void reader(){
        lock.lock();
        try{
            int i = a;
            System.out.println(i);
        }finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        Integer i = 0;
        i = i++;
        System.out.println(i);
        ReentrantLockTest r = new ReentrantLockTest();
        r.writer();
        r.reader();
    }
}
