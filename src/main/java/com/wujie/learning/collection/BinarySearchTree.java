package com.wujie.learning.collection;

import java.util.*;

/**
 * @description: 二叉树
 * @author: wujie
 * @create: 2018-05-24 15:09
 **/
public class BinarySearchTree<T extends Comparable<T>> {

    Node<T> root ;

    //是否包含
    private boolean contains(T t){
        if(root == null || t == null){
            return false;
        }
        Node<T> x = root;
        while(true){
            if(t.compareTo(x.data) < 0){
                if(x.left == null){
                    break;
                }else {
                    x = x.left;
                }
            }else if(t.compareTo(x.data) > 0){
                if(x.right == null){
                    break;
                }else {
                    x = x.right;
                }
            }else {
                return true;
            }
        }
        return false;
    }

    //插入
    private boolean insert(T t){
        if(t == null){
            return false;
        }
        if(root == null){
            root = new Node<T>(null,t);
            return true;
        }

        Node<T> x = root;
        while(true){
            if(t.compareTo(x.data) < 0){
               if(x.left == null){
                    x.left = new Node<T>(x, t);
                    x.left.parent = x;
                    break;
               }else {
                    x = x.left;
               }
            }else if(t.compareTo(x.data) > 0) {
                if(x.right == null){
                    x.right = new Node<T>(x,t);
                    x.right.parent = x;
                    break;
                }else {
                    x = x.right;
                }
            }else {
                System.out.println("数据已存在：" + t);
                return false;
            }
        }
        return true;
    }

    //删除
    private void remove(Node<T> x){
        Node<T> next ;
        Node<T> child;
        Node<T> p = x.parent;

        //直接删除叶子结点
        if(x.left == null && x.right == null){
            //跟结点
            if(p == null){
                root = null;
                return;
            }
            //分左右两种情况
            if(p.left == x){
                p.left = null;
                return;
            }else if (p.right == x){
                p.right = null;
                return;
            }
        }else if (x.left != null && x.right != null){ //中间结点就寻找中序后继，再递归调用
            next = successor(x);
            x.data = next.data;
            remove(next);
        }else {
            if(x.left == null){
                child = x.right;
            }else {
                child = x.left;
            }
            //跟结点
            if(x == root){
                root = child;
                child.parent = null;
                return;
            }

            if(x == p.left){
                p.left = child;
                child.parent = p;
            }else {
                p.right = child;
                child.parent = p;
            }
        }


    }

    //传进来的当前节点作为根节点，因为是中序，所以下一个先查找右子树，再继续按照左中右的顺序循环查找左子树
    //如果当前节点不存在右子树，先查找父节点，直到找到该节点是在某个祖先节点的左子树上
    //说白了就是在中序遍历的时候寻找下一个结点
    public Node<T> successor(Node<T> x) {
        if (x == null)
            return null;
        Node<T> p;
        if (x.right != null) {
            p = x.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }
        else {
            p = x.parent;
            while (p != null && p.left != x) {
                x = p;
                p = x.parent;
            }
            return p;
        }
    }

    //前序遍历（根 左 右）
    private void preOrder(Node<T> x){
        System.out.println(x.data);
        if(x.left != null){
            preOrder(x.left);
        }
        if(x.right != null){
            preOrder(x.right);
        }
    }

    //中序遍历（左 根 右）
    private void inOrder(Node<T> x){
        if(x.left != null){
            inOrder(x.left);
        }
        System.out.println(x.data);
        if(x.right != null){
            inOrder(x.right);
        }
    }

    //后序遍历（左 右 根）
    private void postOrder(Node<T> x){
        if(x.left != null){
            postOrder(x.left);
        }
        if(x.right != null){
            postOrder(x.right);
        }
        System.out.println(x.data);
    }

    //非递归前序遍历
    public void preOrderWithoutRecurs(){
        if (root == null){
            return;
        }

        MyStack<Node<T>> stack = new MyStack<>(50);
        Node<T> current;
        stack.push(root);
        while(!stack.isEmpty()){
            current = stack.getTop();
            if(current.state == 0){
                System.out.println(current.data);
                current.state = 1;
            }else if (current.state == 1){
                if(current.left != null){
                    stack.push(current.left);
                }
                current.state = 2;
            }else if(current.state == 2){
                if(current.right != null){
                    stack.push(current.right);
                }
                current.state = 3;
            }else if (current.state == 3){
                stack.pop();
                current.state = 0;
            }
        }
    }

    //非递归中序遍历
    public void inOrderWithoutRecurs() {
        if (root == null){
            return;
        }

        MyStack<Node<T>> stack = new MyStack<>(50);
        Node<T> current;
        stack.push(root);
        while(!stack.isEmpty()){
            current = stack.getTop();
            if(current.state == 0){
                if(current.left != null){
                    stack.push(current.left);
                }
                current.state = 1;
            }else if (current.state == 1){
                System.out.println(current.data);
                current.state = 2;
            }else if (current.state == 2){
                if(current.right != null){
                    stack.push(current.right);
                }
                current.state = 3;
            }else if (current.state == 3){
                stack.pop();
                current.state = 0;
            }
        }
    }

    //非递归后序遍历
    public void postOrderWithoutRecurs(){
        if (root == null){
            return;
        }

        MyStack<Node<T>> stack = new MyStack<>(50);
        Node<T> current;
        stack.push(root);
        while(!stack.isEmpty()){
            current = stack.getTop();
            if(current.state == 0){
                if(current.left != null){
                    stack.push(current.left);
                }
                current.state = 1;
            }else if (current.state == 1){
                if(current.right != null){
                    stack.push(current.right);
                }
                current.state = 2;
            }else if(current.state == 2){
                System.out.println(current.data);
                current.state = 3;
            }else if (current.state == 3){
                stack.pop();
                current.state = 0;
            }
        }
    }

    //按层遍历
    private void layerTraverse(){
        if (root == null){
            return;
        }
        Node<T> current ;
        LinkedList<Node<T>> queue = new LinkedList<Node<T>>();
        queue.offer(root);
        while(!queue.isEmpty()){
            current = queue.poll();
            System.out.println(current.data);
            if(current.left != null){
                queue.offer(current.left);
            }
            if(current.right != null){
                queue.offer(current.right);
            }
        }
    }

    private static class Node<T>{
        Node<T> left;
        Node<T> right;
        Node<T> parent; //便于查找中序后继
        T data;
        int state;

        Node(Node<T> parent, T data){
            this.left = null;
            this.right = null;
            this.data = data;
            this.parent = parent;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree bTree = new BinarySearchTree();
        bTree.insert(4);
        bTree.insert(2);
        bTree.insert(1);
        bTree.insert(3);
        bTree.insert(5);
        bTree.inOrderWithoutRecurs();
        bTree.remove(bTree.root.left);
        bTree.inOrderWithoutRecurs();
    }
}
