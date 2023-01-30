package com.wujie.learning.thread;

import com.wujie.learning.util.SleepUtils;
import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Lock;

/**
 * @Description: 自定义共享锁测试
 * @Auther: wujie
 * @Date: 2019/11/13 17:10
 * @version: V1.0
 */
public class TwinsLockTest {
    @Test
    public void test(){
        final Lock lock = new TwinsLock();
        class Worker extends Thread{
            @Override
            public void run() {
                while (true){
                    lock.lock();
                    try {
                        SleepUtils.second(1);
                        System.out.println(Thread.currentThread().getName());
                        SleepUtils.second(1);
                    }finally {
                        lock.unlock();
                    }
                }
            }
        }
        // 启动10个线程
        for (int i = 0; i < 10; i++){
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }
        // 每隔1秒换行
        for (int i = 0; i < 10; i++){
            SleepUtils.second(1);
            System.out.println();
        }
    }
}
