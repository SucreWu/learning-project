package com.wujie.learning.util;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Description: 反射
 * @Auther: wujie
 * @Date: 2018/12/13 11:06
 * @version: V1.0
 */
public class TestReflection {

    public static void main(String[] args) throws Throwable {
        Test obj = Test.class.newInstance();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        Method m;
        if ("sayHello".equals(s)){
            m = Test.class.getDeclaredMethod(s);
        }else {
            m = Test.class.getDeclaredMethod("sayWorld");
        }
        System.out.println("当前正在调用" + m.getName() + "方法...");
        m.invoke(obj);

        System.out.println("请输入需要动态加载的成员变量名:");
        s = br.readLine();
        if ("sorter".equals(s)){
            Field field = Test.class.getField(s);
            field.set(obj, Class.forName("com.wujie.learning.util.Sorter").newInstance());
            obj.sort();
        }else {
            System.out.println("Test类不存在该变量名");
        }
        //---------------------------------------------我是分割线-----------------------------------------------------
        // 方法名从本地文件中读取
        System.out.println("读取配置文件...");
        // 参数为空
        File directory = new File("");
        String route = directory.getCanonicalPath();
        br = new BufferedReader(new FileReader(route + "/src/main/resources/config"));
        s = br.readLine();
        if ("sayHello".equals(s)){
            m= Test.class.getDeclaredMethod(s);
        }else {
            m= Test.class.getDeclaredMethod("sayWorld");
        }
        System.out.println("配置文件中的方法是：" + m.getName());
        m.invoke(obj);

        br.close();
    }
}

class Test {
    public Sorter sorter;

    public void sayHello() {
        System.out.println("hello");
    }

    public void sayWorld() {
        System.out.println("world");
    }

    public void sort(){
        int[] array = {3, 7, 0, 2, 5, 9};
        for (int i: array){ System.out.print(i);}
        sorter.sort(array);
        System.out.println("\n经过排序后：");
        for (int i: array){ System.out.print(i);}
        System.out.println("");
    }
}

class Sorter{

    public void sort(int[] array){
        Arrays.sort(array);
    }
}
