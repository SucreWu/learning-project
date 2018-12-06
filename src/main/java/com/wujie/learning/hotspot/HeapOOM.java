package com.wujie.learning.hotspot;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: JAVA堆溢出
 * @Auther: wujie
 * @Date: 2018/12/6 14:09
 * @version: V1.0
 */
public class HeapOOM {

    static class OOMObject{
    }

    /**
     * 需要设置虚拟机启动参数： -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
     * @param args
     */
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();

        // 不断创建对象，并保证GC Roots到对象之间有可达路径来避免垃圾回收机制清除这些对象
        // 那么在对象数量达到最大堆的容量限制后就会产生内存溢出异常
        while (true){
            list.add(new OOMObject());
        }
    }
}
