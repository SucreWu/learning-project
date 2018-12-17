package com.wujie.learning.design_pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description: 动态代理UserDao
 * @Auther: wujie
 * @Date: 2018/12/17 15:10
 * @version: V1.0
 */
public class InvocationUserDao implements InvocationHandler {

    UserDao target;

    public InvocationUserDao(UserDao userDao){
        this.target = userDao;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("log : " + method.getName() + " invoked with " + args.toString());
        return method.invoke(target, args);
    }
}
