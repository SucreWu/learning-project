package com.wujie.learning.collection;

/**
 * @description:  链表
 * @author: wujie
 * @create: 2018-05-24 9:39
 **/
public class MyLinkedList<E> {

    Node<E> first;
    Node<E> last;
    int size = 0;

    public int size(){
        return size;
    }

    public void add(E e){
        linkLast(e);
    }

    public void add(int index,E e){
        checkOutOfBounds(index);
        //索引值等于数组长度时，直接添加至末尾，所以在判断数组越界的时候是>size 而不是>=size
        if(index == size){
            linkLast(e);
        }else{
            linkBefore(e,node(index));
        }
    }

    public E remove(int index){
        checkOutOfBounds(index);
        return unlink(node(index));
    }

    //移除链表中首个值为e的节点
    public boolean remove(E e){
        if(e == null){
            for(Node<E> x = first; x != null; x = x.next){
                if(x.data == null){
                    unlink(x);
                    return true;
                }
            }
        }else {
            for(Node<E> x = first; x != null; x = x.next){
                if(e.equals(x.data)){
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    public E set(int index,E e){
        checkOutOfBounds(index);
        Node<E> x = node(index);
        E oldValue = x.data;
        x.data = e;
        return oldValue;
    }
    public boolean contains(E e){
        return indexOf(e) != -1;
    }

    public int indexOf(E e){
        int index = 0;
        if(e == null){
            for(Node<E> x = first; x != null; x = x.next){
                if(x.data == null){
                    return index;
                }
                index++;
            }
        }else {
            for(Node<E> x = first; x != null; x = x.next){
                if(e.equals(x.data)){
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    public E get(int index){
        checkOutOfBounds(index);
        return node(index).data;
    }

    //在尾节点后添加新节点
    public void linkLast(E e){
        Node<E> l = last;
        Node<E> newNode = new Node<E>(l,null,e);
        last = newNode;
        if(l == null){
            first = newNode;
        }else{
            l.next = newNode;
        }
        size++;
    }

    //在指定节点前添加新节点
    public void linkBefore(E e,Node<E> next){
        Node<E> prev = next.prev;
        Node<E> newNode = new Node<E>(prev,next,e);
        if(prev == null){
            first = newNode;
        }else{
            prev.next = newNode;
        }
        next.prev = newNode;
        size++;
    }

    //移除指定节点
    public E unlink(Node<E> x){
        Node<E> prev = x.prev;
        Node<E> next = x.next;
        E oldValue = x.data;
        x.data = null;
        if(prev == null){
            first = next;
            // 这里不用置为null是因为在手动将x节点的三个成员变量都置为null后，会加速垃圾回收，自然会成为null
            //next.prev = null;
        }else {
            prev.next = next;
            x.prev = null;
        }
        if(next == null){
            last = prev;
        }else {
            next.prev = prev;
            x.next = null;
        }
        size--;
        return oldValue;
    }

    //根据索引值返回对应节点
    public Node<E> node(int index){
        checkOutOfBounds(index);
        if(index < size>>1){
            Node<E> x = first;
            for(int i = 0; i < index; i++){
                x = x.next;
            }
            return x;
        }else{
            Node<E> x = last;
            //注意index从0开始计数的，长度为3的数组，index最多就是2，所以从后往前查找时是size-1
            for(int i = size-1; i > index;i--){
                x = x.prev;
            }
            return x;
        }
    }

    //检查数组是否越界
    public void checkOutOfBounds(int index){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("index is : " + index + ",size is : " + size );
        }
    }

    private static class Node<E>{
        Node<E> prev;
        Node<E> next;
        E data;

        Node(Node<E> prev,Node<E> next,E data){
            this.prev = prev;
            this.next = next;
            this.data = data;
        }
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.add(1);
        list.add("a");
        list.add(3333L);
        list.add(3,"zzzz");
        list.remove(2);
        list.set(2,"zzz");
        System.out.println(list.indexOf("zzz"));
        System.out.println(list.contains("zzz"));
        for (int i = 0; i < list.size; i++) {
            System.out.println(list.get(i));
        }
    }
}
