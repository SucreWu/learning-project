package com.wujie.learning.thread;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Auther: wujie
 * @Date: 2019/10/28 16:07
 * @version: V1.0
 */
public class ConnectionPoolTest {

    static ConnectionPool pool = new ConnectionPool(10);

    // 保证所有ConnectionRunner能够同时开始，通过在每个子线程的run方法内先调用线程计数器的await()方法，
    // 然后等线程启动后再在main线程中调用countDown()方法
    static CountDownLatch start = new CountDownLatch(1);

    // 确保main会等待所有ConnectionRunner结束后再继续执行
    static CountDownLatch end;

    static class ConnectionRunner implements Runnable{

        // 控制循环获取数据库连接的次数
        int count;
        // 获取到的数量
        AtomicInteger got;
        // 未获取到的数量
        AtomicInteger notGot;

        // 这里两个原子变量都是通过外部注入，用的main线程中的同一个变量保证线程安全
        public ConnectionRunner(int count, AtomicInteger got, AtomicInteger notGot){
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        @Override
        public void run() {
            try{
                start.await();
            }catch (InterruptedException e){
            }
            while (count > 0){
                try{
                    // 从连接池中获取连接，如果1000ms内无法获取到，将会返回null
                    // 分别统计连接获取的数量got和未获取到的数量notGot
                    Connection connection = pool.fetchConnection(1000);
                    if (connection != null){
                        try{
                            connection.createStatement();
                            connection.commit();
                        }finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    }else {
                        notGot.incrementAndGet();
                    }
                }catch (Exception e){
                }finally {
                    count--;
                }
            }
            end.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        // 可修改的线程数量
        int threadCount = 20;
        end = new CountDownLatch(threadCount);
        // 线程循环获取次数
        int count = 20;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();
        for (int i = 0; i < threadCount; i++){
            Thread thread = new Thread(new ConnectionRunner(count, got, notGot),
                    "ConnectionRunnerThread");
            thread.start();
        }
        start.countDown();
        end.await();
        System.out.println("总计获取数据库连接共" + count * threadCount + "次");
        System.out.println("成功获取次数：" + got);
        System.out.println("获取失败次数：" + notGot);
    }
}
