package com.wujie.learning.design_pattern.proxy;

/**
 * @Description: 代理类
 * @Auther: wujie
 * @Date: 2018/12/17 14:26
 * @version: V1.0
 */
public class UserDaoProxy implements UserDao{

    // 需要代理的目标
    private UserDao target;

    public UserDaoProxy(UserDao s){
        target = s;
    }

    @Override
    public void insert(int id) {
        System.out.println("Proxy log : " + id);
        target.insert(id);
        System.out.println(id + " has been inserted");
    }

    public void setTarget(UserDao target) {
        this.target = target;
    }
}
