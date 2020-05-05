package com.wujie.learning.thread;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.LinkedList;

public class JVMTest {
    ThreadLocal<Thread> threadLocal = new ThreadLocal<>();

    byte[] b = new byte[1024*100];  // 100KB

    public void createOrder(){
        Thread thread = new Thread(() -> {int i =0;});
            threadLocal.set(thread);
    }

    public static void main(String[] args) throws InterruptedException{
        JVMTest test = new JVMTest();
        test.createOrder();
        test.threadLocal.get().join();
        LinkedList<JVMTest> list = new LinkedList<>();
        while (true){
            list.add(test);
            Thread.sleep(10);
        }

    }
}
