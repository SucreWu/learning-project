package com.wujie.learning.util;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Auther: wujie
 * @Date: 2019/10/22 17:10
 * @version: V1.0
 */
public class SleepUtils {
    public static final void second(long seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
