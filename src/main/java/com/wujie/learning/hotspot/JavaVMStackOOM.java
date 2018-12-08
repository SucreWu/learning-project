package com.wujie.learning.hotspot;

/**
 * @Description: 虚拟机栈和本地方法栈溢出
 * OutOfMemoryError
 * 如果虚拟机在扩展栈时无法申请到足够的内存空间，则抛出OutOfMemoryError异常
 * @Auther: wujie
 * @Date: 2018/12/8 15:01
 * @version: V1.0
 */
public class JavaVMStackOOM {
    /**
     * VM Args: -Xss2M
     * 由于系统分配给某个进程的内存是有限的，所以每个线程分配到的栈容量越大，在循环建立线程的时候就更容易把内存耗尽
     */
    public void keepRun(){
        while (true){
        }
    }

    public void staticLeakByThread(){
        while (true){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    keepRun();
                }
            });
            thread.start();
        }
    }

    // 跑之前建议把该关的都关了，做好死机的准备
    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.staticLeakByThread();
    }

}
