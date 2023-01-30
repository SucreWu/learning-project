package com.wujie.learning.thread;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程计数器
 * @author Administrator
 *
 */
public class CountDownLatchTest {
    private static CountDownLatch latch = new CountDownLatch(10);
    private static CountDownLatch longlatch = new CountDownLatch(1);
    private static ExecutorService executor = Executors.newFixedThreadPool(2);

    static class TestThread implements Runnable {

        private CountDownLatch latch;

        public TestThread(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行完毕————" + Thread.currentThread().getName());
            latch.countDown();
        }

    }

    static class TestLongThread implements Runnable {

        private CountDownLatch latch;

        public TestLongThread(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("耗时较慢的线程执行完毕————" + Thread.currentThread().getName());
            latch.countDown();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        executor.execute(new TestLongThread(longlatch));
        longlatch.await(1, TimeUnit.SECONDS);
        //两次线程去操作逻辑
        for (int i = 0; i < 10; i++) {
            executor.execute(new TestThread(latch));
        }
        //保证所有线程执行完毕，再执行下面程序
        latch.await();
        executor.shutdown();
    }

}
