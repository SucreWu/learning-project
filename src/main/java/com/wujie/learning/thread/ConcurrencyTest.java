package com.wujie.learning.thread;

/**
 * @Description: 并发与串行执行速度
 * @Auther: wujie
 * @Date: 2019/10/12 09:59
 * @version: V1.0
 */
public class ConcurrencyTest {
    private static final long count = 100001;
    // 并发累加操作低于百万次速度就会比串行慢，因为线程创建和上下文切换的开销
//    private static final long count = 1000000001;

    private static void concurrency() throws InterruptedException{
        long start = System.currentTimeMillis();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (long i = 0; i < count; i++){
                    a += 5;
                }
            }
        });
        thread.start();
        int b = 0;
        for (long i = 0; i < count; i++){
            b--;
        }
        thread.join();
        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency :" + time + "ms, b=" + b);
    }

    private static void serial(){
        long start = System.currentTimeMillis();
        int a = 0, b = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        for (long i = 0; i < count; i++){
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency :" + time + "ms, b=" + b + ",a=" + a);
    }

    public static void main(String[] args) throws InterruptedException{
        concurrency();
        serial();
    }
}
