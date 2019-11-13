package com.wujie.learning.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @Description:
 * @Auther: wujie
 * @Date: 2019/10/22 14:59
 * @version: V1.0
 */
public class MultiThread {
    public static void main(String[] args) {
        // 获取java线程管理MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 不需要获取同步monitor和synchronizer信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(
                false, false);
        // 遍历线程信息，仅打印线程ID和线程名称信息
        for (ThreadInfo threadInfo : threadInfos){
            System.out.println("[" + threadInfo.getThreadId()
                    + "]" + threadInfo.getThreadName());
        }

    }

    /**RUN
     * [5]Monitor Ctrl-Break
     * [4]Signal Dispatcher     分发处理发送给JVM信号的线程
     * [3]Finalizer             调用对象finalize方法的线程
     * [2]Reference Handler     清除Reference的线程
     * [1]main                  main线程，用户程序入口
     */

    /**DEBUG
     * [7]JDWP Command Reader
     * [6]JDWP Event Helper Thread
     * [5]JDWP Transport Listener: dt_socket
     * [4]Signal Dispatcher
     * [3]Finalizer
     * [2]Reference Handler
     * [1]main
     */

    //RUN的5和debug的567都是intellij的问题
    // 比如IDEA 在 run 的时候会通过 -javaagent 参数设置自己的监视器（lib/idea_rt.jar）
    // 就是在反射方式执行用户程序之前，在当前线程的线程组之中又开了一个Ctrl Break的Monitor的Socket线程，去做监听
    // 在阅读《深入理解Java虚拟机》一书中，执行代码清单12-1的例子时，疑似发现bug？ - ETIN的回答 - 知乎
    // https://www.zhihu.com/question/59297272/answer/164163911
}
