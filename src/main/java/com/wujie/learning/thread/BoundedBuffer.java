package com.wujie.learning.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: Condition实现生产消费者模式
 * @Auther: wujie
 * @Date: 2019/11/11 16:19
 * @version: V1.0
 */
public class BoundedBuffer {

    final Lock lock = new ReentrantLock();

    // Condition 依赖于lock来生产
    final Condition notFull = lock.newCondition();

    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[100];
    int putptr, takeptr, count;

    // 生产
    public void put(Object x)throws InterruptedException{
        lock.lock();
        try{
            while (count == items.length){
                // 队列已满，等待，直到 not full 才能继续生产
                notFull.await();
            }
            items[putptr] = x;
            if (++putptr == items.length) putptr = 0;
            ++count;
            notEmpty.signal(); // 生产成功，队列已经 not empty 了，发个通知出去
        }finally {
            lock.unlock();
        }

    }

    // 消费
    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                // 队列为空，等待，直到队列 not empty，才能继续消费
                notEmpty.await();
            Object x = items[takeptr];
            if (++takeptr == items.length) takeptr = 0;
            --count;
            notFull.signal(); // 被我消费掉一个，队列 not full 了，发个通知出去
            return x;
        } finally {
            lock.unlock();
        }
    }

}
