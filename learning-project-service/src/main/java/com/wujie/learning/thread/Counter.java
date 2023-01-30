package com.wujie.learning.thread;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 线程安全计数器
 * @Auther: wujie
 * @Date: 2019/10/14 15:57
 * @version: V1.0
 */
public class Counter {

    private AtomicInteger atomicI = new AtomicInteger(0);

    private int i = 0;

    /**
     * 使用CAS实现线程安全计数器
     */
    private void safeCount() {
//        atomicI.incrementAndGet();
        // 注释的那一行跟下面这段代码本质是一样的，底层都是调用compareAndSwapInt这个native方法
        for (; ; ) {
            int a = atomicI.get();
            boolean suc = atomicI.compareAndSet(a, ++a);
            if (suc){
                break;
            }
        }
    }

    /**
     * 非线程安全计数器
     */
    private void count(){
        i++;
    }

    public static void main(String[] args) {
        final Counter counter = new Counter();
        List<Thread> list = new LinkedList<Thread>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++){
            Thread r = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++){
                        counter.count();
                        counter.safeCount();
                    }
                }
            });
            list.add(r);
        }
        for (Thread t : list){
            t.start();
        }
        // 等待所有线程都执行完毕
        for (Thread t : list){
            try {
                t.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println(counter.i);
        System.out.println(counter.atomicI.get());
        System.out.println(System.currentTimeMillis() - start);
    }
}
