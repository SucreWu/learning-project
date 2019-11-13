package com.wujie.learning.util;

import sun.jvm.hotspot.oops.Instance;

/**
 * @Description: 双重检查锁
 * @Auther: wujie
 * @Date: 2019/10/22 10:31
 * @version: V1.0
 */
public class SafeDoubleCheckedLocking {

    // volatile关键字的作用是防止在多线程的环境下，对象初始化和设置对象实例指向内存空间的指令重排序。
    private volatile static SafeDoubleCheckedLocking instance;

    public static SafeDoubleCheckedLocking getInstance1(){
        if (instance == null){
            synchronized(SafeDoubleCheckedLocking.class){
                if(instance == null){
                    instance = new SafeDoubleCheckedLocking();
                }
            }
        }
        return instance;
    }

    // 类初始化的解决方案，因为初始化对象会加锁，这个锁可以同步多个线程对同一个类的初始化
    // 所以多线程环境下线程并发获取实例对象时，只有一个线程会竞争到class对象的初始化锁
    private static class InstanceHolder{
        public static SafeDoubleCheckedLocking instance = new SafeDoubleCheckedLocking();
    }

    public static SafeDoubleCheckedLocking getInstance2(){
        return InstanceHolder.instance;
    }

}
