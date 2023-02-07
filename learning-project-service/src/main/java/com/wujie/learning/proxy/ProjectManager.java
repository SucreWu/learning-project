package com.wujie.learning.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author wujie
 * @Description
 * @date 2023/2/6 16:43
 */
public class ProjectManager implements InvocationHandler {

    private Developer developer;

    public ProjectManager(Developer developer) {
        this.developer = developer;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        // 被代理对象中的方法
        method.invoke(developer, null);
        after();
        return null;
    }

    private void before() {
        System.out.println("安排任务");
    }

    private void after() {
        System.out.println("每日总结");
    }
}
