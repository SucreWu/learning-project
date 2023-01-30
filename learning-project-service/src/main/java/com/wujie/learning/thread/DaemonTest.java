package com.wujie.learning.thread;

import com.wujie.learning.util.SleepUtils;

/**
 * @Description: 守护线程的优先级比较低
 * @Auther: wujie
 * @Date: 2019/10/22 17:08
 * @version: V1.0
 */
public class DaemonTest {
    // Daemon线程时一种支持型线程，因为它主要被用作程序中后台调度以及支持性工作。这意味着，
    // 当一个Java虚拟机中不存在非Daemon线程的时候，Java虚拟机将会退出。
    // 可以通过调用Thread.setDaemon(true)将线程设置为Daemon线程
    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonRunner(), "DaemonRunner");
        thread.setDaemon(true);
        thread.start();
    }

    static class DaemonRunner implements Runnable{
        @Override
        public void run() {
            try {
                SleepUtils.second(10);
            } finally {
                System.out.println("DaemonThread finally run.");
            }

        }
    }
}
