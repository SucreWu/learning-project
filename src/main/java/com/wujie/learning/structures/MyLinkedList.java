package com.wujie.learning.structures;

import com.sun.jmx.remote.internal.ArrayQueue;

/**
 * @description:  链表
 * @author: wujie
 * @create: 2018-05-24 9:39
 **/
public class MyLinkedList<T> {

    Node<T> first;
    Node<T> last;
    int size;

    public MyLinkedList(){

    }

    /**
     * 检查是否数组越界
     * @param index
     */
    public void checkOutOfBounds(int index){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException(String.format("index is: %d,size is: %d",index,size));

        }
    }

    /**
     * 二分查找，根据索引值返回对应节点
     * @param index
     * @return
     */
    private Node<T> node(int index){
        checkOutOfBounds(index);
        if (index > size>>1){
            Node<T> x = last;
            for(int i = 0;i < size - index;i++){
                x = x.prev;
            }
            return x;
        }else{
            Node<T> x = first;
            for(int i = 0;i < index -1;i++){
                x = x.next;
            }
            return x;
        }
    }

    /**
     * 替换索引位置的节点，并将新节点的next节点指向原节点
     * @param t
     * @param x
     */
    private void linkBefore(T t,Node<T> x){
        Node<T> newNode = new Node<T>(x.prev,x,t);
        if(x.prev == null){
            first = newNode;
        }else {
            x.prev.next = newNode;
        }
        x.prev = newNode;
        size++;
    }

    /**
     * 根据t的值将新建的节点插入到链表尾部
     * @param t
     */
    private void linkLast(T t){
        Node<T> l = last;
        Node<T> newNode = new Node<>(last,null,t);
        if (last == null){
            first = newNode;
        }else {
            l.next = newNode;
        }
        last = newNode;
        size++;
    }

    /**
     * 移除指定节点
     * @param x
     * @return
     */
    private T unlink(Node<T> x){
        Node<T> next = x.next;
        Node<T> prev = x.prev;
        T t = x.data;
        x.data = null;
        //根据不同情况分别将next prev置为null，help GC
        if (prev == null){
            first = next;
        }else {
            prev.next = next;
            x.prev = null;
        }
        if (next ==null){
           last = prev;
        }else {
            next.prev = prev;
            x.next = null;
        }
        size--;
        return t;
    }

    /**
     * 移除头结点，参数为first
     * @param f
     * @return
     */
    private T unlinkFirst(Node<T> f){
        Node<T> x = f.next;
        T t = f.data;
        f.next = null;
        f.data = null;
        first = x;
        if (x == null){
           last = null;
        }else {
            x.prev = null;
        }
        size--;
        return t;
    }

    /**
     * 移除尾节点，参数为last
     * @param l
     * @return
     */
    private T unlinkLast(Node<T> l){
        Node<T> x = l.prev;
        T t = l.data;
        l.prev = null;
        l.data = null;
        last = x;
        if (x == null){
            first = null;
        }else {
            x.next = null;
        }
        size--;
        return t;
    }

    /**
     * 返回指定元素索引位置
     * @param t
     * @return
     */
    private int indexOf(T t){
        int index = 0;
        if (t == null){
           for(Node<T> x = first; x == null; x = x.next){
               if (x.data == null){
                   return index;
               }
               index++;
           }
        }else {
            for(Node<T> x = first; x == null; x = x.next){
                if (t.equals(x.data)){
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    /**
     * 是否包含指定元素
     * @param t
     * @return
     */
    public boolean contains(T t){
        return indexOf(t) != -1;
    }

    /**
     * 在链表末尾添加元素
     * @param t
     */
    public void add(T t){
        linkLast(t);
    }

    /**
     * 在链表索引位置添加元素
     * @param t
     * @param index
     */
    public void add(T t,int index){
        checkOutOfBounds(index);
        if (index == size){
            linkLast(t);
        }else {
            linkBefore(t,node(index));
        }
    }

    /**
     * 移除链表第一个元素
     * @return
     */
    public T removeFirst(){
        final Node<T> f = first;
        if (f == null){
            throw new NoSuchElementException();
        }
        return unlinkFirst(f);
    }

    /**
     * 移除链表最后一个元素
     * @return
     */
    public T removeLast(){
        final Node<T> l = last;
        if (l == null){
            throw new NoSuchElementException();
        }
        return unlinkLast(l);
    }

    /**
     * 移除链表索引位置的元素
     * @param index
     * @return
     */
    public T remove(int index){
        checkOutOfBounds(index);
        return unlink(node(index));
    }


    public T get(int index){
        checkOutOfBounds(index);
        return node(index).data;
    }

    public T set(T t,int index){
        checkOutOfBounds(index);
        Node<T> x = node(index);
        T oldValue = x.data;
        x.data = t;
        return oldValue;
    }


    /**
     * 内部节点类
     * @param <T>
     */
    private static class Node<T>{

        Node<T> prev;
        Node<T> next;
        T data;

        Node(Node<T> prev,Node<T> next,T data){
            this.prev = prev;
            this.next = next;
            this.data = data;
        }
    }

    /**
     * 链表节点为空异常
     */
    public static class NoSuchElementException extends RuntimeException {

        public NoSuchElementException() {
            super();
        }

        public NoSuchElementException(String s) {
            super(s);
        }
    }

    public static void main(String[] args) {

//        MyLinkedList<String> list= new MyLinkedList<String>();
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        System.out.println(list.contains("3"));
//        System.out.println(list.remove(0));
//        System.out.println(list.get(0));
        ArrayQueue<Integer> array = new ArrayQueue<Integer>(10);
        array.add(3);

    }
}
