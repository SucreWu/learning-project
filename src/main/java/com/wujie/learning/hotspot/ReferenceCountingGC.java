package com.wujie.learning.hotspot;

/**
 * @Description: 引用计数算法，JAVA主流的虚拟机里并没有用这个来判断对象是否存活
 * 做法是给对象添加一个引用计数器，每当有其他地方引用它后值加一，引用失效后值减一，值为0表示对象不会再被使用
 * 注：如果两个对象相互引用，则不会被这种方式回收
 * @Auther: wujie
 * @Date: 2018/12/8 16:13
 * @version: V1.0
 */
public class ReferenceCountingGC {

    public Object instance = null;

    private static final int _1MB = 1024*1024;

    /**
     * 这个对象的作用是占点内存，便于在GC日志中看清楚是否被回收过
     */
    private byte[] bigSize = new byte[2 * _1MB];

    public static void testGC(){
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB= null;

        /**
         * VM Args: -XX:+PrintGCDetails
         * 用于查看GC日志
         * 运行后可以发现这里的objA和objB被回收了，说明JAVA虚拟机没有使用引用计数算法
         */
        System.gc();
    }

    public static void main(String[] args) {
        ReferenceCountingGC.testGC();
    }
}
