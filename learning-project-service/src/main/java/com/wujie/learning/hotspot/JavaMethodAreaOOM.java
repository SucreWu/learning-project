package com.wujie.learning.hotspot;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description: 方法区和运行时常量池溢出
 * 运行时产生大量的类去填满方法区，直到溢出
 * OutOfMemoryError
 * @Auther: wujie
 * @Date: 2018/12/8 15:21
 * @version: V1.0
 */
public class JavaMethodAreaOOM {

    static class OOMObject{
    }

    /**
     * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
     * 借助CGLib使方法区出现内存溢出
     * Java HotSpot(TM) 64-Bit Server VM warning: ignoring option PermSize=10M; support was removed in 8.0
     * Java HotSpot(TM) 64-Bit Server VM warning: ignoring option MaxPermSize=10M; support was removed in 8.0
     * JAVA8.0之后的版本不支持
     */
    public static void main(String[] args) {
        while (true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(objects, args);
                }
            });
            enhancer.create();
        }
    }
}
