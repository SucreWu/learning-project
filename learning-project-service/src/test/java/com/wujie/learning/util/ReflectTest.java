package com.wujie.learning.util;

import com.wujie.learning.entity.BaseVo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectTest<T extends BaseVo> {

    public void test() throws Exception{
        Class<?> c = Class.forName("com.wujie.learning.entity.ProjectVo");
//        Method method = c.getDeclaredMethod("setName", String.class);
        Field f = getField(c.newInstance(), "name");
        f.setAccessible(true);
        Class<?> c2 = f.getType();
        Method method1 = getDeclaredMethod(c.newInstance(), "setName", c2);

        System.out.println(method1.getName());
    }

    public static void main(String[] args) throws Exception {
        ReflectTest r = new ReflectTest();
        r.test();

    }

    public static Field getField(Object object, String fieldName){
        Field field = null ;
        for(Class<?> clazz = object.getClass() ; clazz != Object.class ; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName) ;
                return field ;
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static Method getDeclaredMethod(Object object, String methodName, Class<?> ... parameterTypes){
        Method method = null ;
        for(Class<?> clazz = object.getClass() ; clazz != Object.class ; clazz = clazz.getSuperclass()) {
            try {
                method = clazz.getDeclaredMethod(methodName, parameterTypes) ;
                return method ;
            } catch (Exception e) {
            }
        }
        return null;
    }
}
