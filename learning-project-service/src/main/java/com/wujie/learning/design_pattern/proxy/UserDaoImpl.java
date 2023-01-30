package com.wujie.learning.design_pattern.proxy;

/**
 * @Description: 真实被代理的对象
 * @Auther: wujie
 * @Date: 2018/12/17 14:25
 * @version: V1.0
 */
public class UserDaoImpl implements UserDao{

    @Override
    public void insert(int id) {
        System.out.println("UserDaoImpl insert: " + id);
    }

    public void delete(int id) {
        System.out.println("UserDaoImpl delete: " + id);
    }
}
