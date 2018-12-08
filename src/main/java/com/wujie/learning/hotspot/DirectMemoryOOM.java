package com.wujie.learning.hotspot;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Description: 本机直接内存溢出
 * @Auther: wujie
 * @Date: 2018/12/8 15:42
 * @version: V1.0
 */
public class DirectMemoryOOM {

    /**
     * VM Args: -Xmx20M -XX:MaxDirectMemorySize=10M
     */
    private static final int _1MB = 1024*1024;

    public static void main(String[] args) throws Exception{
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true){
            unsafe.allocateMemory(_1MB);
        }
    }
}
