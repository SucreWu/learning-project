package com.wujie.learning.proxy;

import com.wujie.learning.service.Employee;

import java.lang.reflect.Proxy;

/**
 * @author wujie
 * @Description
 * @date 2023/2/6 16:48
 */
public class ProxyTest {

    public static void main(String[] args) {
        // 创建被代理对象，第二个入参为被代理对象实现的接口集合，第三个入参为代理对象
        Employee employee = (Employee) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(),
                new Class[]{Employee.class}, new ProjectManager(new Developer()));
        employee.work();
    }
}
