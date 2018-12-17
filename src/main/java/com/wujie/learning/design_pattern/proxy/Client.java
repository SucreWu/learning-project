package com.wujie.learning.design_pattern.proxy;

import java.lang.reflect.Proxy;

/**
 * @Description:
 * @Auther: wujie
 * @Date: 2018/12/17 14:36
 * @version: V1.0
 */
public class Client {
    public static void main(String[] args) {
//        UserDao u = new UserDaoProxy(new UserDaoImpl());
//        u.insert(123456);
        dynamicProxy();
    }

    public static void dynamicProxy(){
        UserDao target = new UserDaoImpl();
        UserDao proxy = (UserDao)Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationUserDao(target)
        );
        proxy.insert(123456);
    }
}
