package com.wujie.learning.collection;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @description: 栈（乱写的，非线程安全）
 * @author: wujie
 * @create: 2018-05-28 15:22
 **/
public class MyStack<T> {
    private ArrayList<T> list;
    private int top = 0; //栈顶索引

    public MyStack(int size) {
        this.list = new ArrayList<T>(size);
    }

    //获取长度
    public int size(){
        return list.size();
    }

    //获取实际长度
    public int realSize(){
        return top;
    }

    //入栈
    public void push(T t) {
        list.add(t);
        top++;
    }

    //出栈
    public T pop(){
        T oldValue = list.get(top-1);
        list.remove(--top);
        return oldValue;
    }

    //获取栈顶元素
    public T getTop(){
        return list.get(top-1);
    }

    //判断是否为空
    public boolean isEmpty() {
        return top == 0;
    }

    //检查是否数组越界
    @Deprecated
    private void checkOutOfBounds(int index) {
        if (index > size() || index < 0)
            throw new IndexOutOfBoundsException("size is : " + size() + ",index is : " + index);
    }

    public static void main(String[] args) {
        MyStack<Object> s = new MyStack<>(20);
        s.push("aaa");
        s.push(3);
        s.push(3.5);
        System.out.println(s.size());
        System.out.println(s.getTop());
        System.out.println(s.pop());
        System.out.println(s.getTop());
        System.out.println(s.realSize());
    }
}