package com.wujie.learning.structures;

import java.util.*;

/**
 * @Description:
 * @Auther: wujie
 * @Date: 2019/10/10 16:21
 * @version: V1.0
 */
public class HashSetTest {

    static class Fruit{
        private String name;
        private Integer price;

        public Fruit(){ }

        public Fruit(String name, Integer price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public Integer getPrice() {
            return price;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        // 不重写hashcode和equals方法时，由于添加的是两个不同的对象，
        // hashcode值一定不同所以可以被添加进hashSet
        public int hashCode(){
            return this.name.hashCode() + price;
        }

        public boolean equals(Object obj){
            if (obj instanceof Fruit){
                Fruit f = (Fruit) obj;
                return this.name.equals(f.name) && this.price.equals(f.price);
            }else {
                return false;
            }
        }

        @Override
        public String toString() {
            return name + " " + price;
        }
    }

    // 问题：现在有一批数据，要求不能重复存储元素，而且要排序。ArrayList 、 LinkedList不能去除重复数据。
    // HashSet可以去除重复，但是是无序。所以这时候就要使用TreeSet了
    // 既然TreeSet可以自然排序,那么TreeSet必定是有排序规则的。
    static class Person implements Comparable{

        private String name;
        private int age;
        private String gender;

        public Person(){}

        public Person(String name, int age, String gender){
            this.name = name;
            this.age = age;
            this.gender = gender;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String getGender() {
            return gender;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        @Override
        public String toString() {
            return "name: " + this.name + "，age: " + this.age + "，gender: " + this.gender;
        }

        @Override
        public int compareTo(Object o) {
            Person p = (Person) o;
            if (this.age > p.age){
                return 1;
            }
            if (this.age < p.age){
                return -1;
            }
            return this.name.compareTo(p.name);
        }
    }

    static class MyComparator implements Comparator {

        public int compare(Object o1, Object o2) {
            Fruit f1 = (Fruit) o1;
            Fruit f2 = (Fruit) o2;
            if (f1.getPrice() > f2.getPrice()) {
                return 1;
            }
            if (f1.getPrice() < f2.getPrice()) {
                return -1;
            }
            return f1.getName().compareTo(f2.getName());
        }

    }

    public static void main(String[] args) {

        Set<Fruit> set = new HashSet<Fruit>();
        set.add(new Fruit("apple", 6));
        set.add(new Fruit("orange", 9));
        set.add(new Fruit("banana", 5));
        set.add(new Fruit("peach", 4));
        Boolean add = set.add(new Fruit("peach", 4));
        System.out.println(add);
        System.out.println(set.size());

        TreeSet<Person> set2 = new TreeSet<Person>();
        set2.add(new Person("aaa", 19, "male"));
        set2.add(new Person("bbb", 11, "male"));
        set2.add(new Person("ddd", 25, "male"));
        set2.add(new Person("eee", 22, "female"));
        set2.add(new Person("ccc", 21, "female"));
        Iterator i = set2.iterator();
        while(i.hasNext()){
            System.out.println(i.next());
        }

        TreeSet set3 = new TreeSet(new MyComparator());
        set3.add(new Fruit("apple", 6));
        set3.add(new Fruit("orange", 9));
        set3.add(new Fruit("banana", 5));
        set3.add(new Fruit("peach", 4));
        set3.add(new Fruit("apple2", 4));
        Iterator i2 = set3.iterator();
        while(i2.hasNext()){
            System.out.println(i2.next());
        }

    }

}



