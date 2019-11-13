package com.wujie.learning.structures;

import java.util.concurrent.*;

/**
 * @Description:
 * @Auther: wujie
 * @Date: 2019/10/8 16:58
 * @version: V1.0
 */
public class BlockingQueueTest {

    // 果篮
    public static class Basket{
        BlockingQueue<String> basket = new ArrayBlockingQueue<>(3);

        public void produce() throws InterruptedException {
            basket.put("An apple");
        }

        public String consume() throws InterruptedException{
            String apple = basket.take();
            return apple;
        }

        public int getAppleNumber(){
            return basket.size();
        }
    }

    // 测试方法
    public static void basketTest(){
        final Basket basket =  new Basket();
        // 定义生产者
        class Producer implements Runnable{

            @Override
            public void run() {
                try {
                    while (true){
//                        System.out.println("开始生产苹果...");
                        // 生产苹果
                        basket.produce();
                        System.out.println("生产完毕，当前有"
                                + basket.getAppleNumber() + "个苹果。");
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // 定义消费者
        class Consumer implements Runnable{

            @Override
            public void run() {
                try {
                    while (true){
//                        System.out.println("开始消费...");
                        basket.consume();
                        System.out.println("消费完毕，还剩"
                                + basket.getAppleNumber() + "个苹果。");
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        ExecutorService service = Executors.newCachedThreadPool();
        Producer producer = new Producer();
        Consumer consumer1 = new Consumer();
        Consumer consumer2 = new Consumer();
        service.submit(producer);
        service.submit(consumer1);
        service.submit(consumer2);
        try {
            // 一共运行10秒停止
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdownNow();
    }

    public static void main(String[] args) {
        BlockingQueueTest.basketTest();
    }
}
