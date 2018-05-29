package com.wujie.learning.collection;

import java.util.LinkedList;
import java.util.Queue;

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

    //插入操作
    private boolean insert(T t){
        if(t == null){
            return false;
        }
        if(root == null){
            root = new Node<T>(null,null,t);
            return true;
        }

        Node<T> x = root;
        while(true){
            if(t.compareTo(x.data) < 0){
               if(x.left == null){
                    x.left = new Node<T>(null,null,t);
                    break;
               }else {
                    x = x.left;
               }
            }else if(t.compareTo(x.data) > 0) {
                if(x.right == null){
                    x.right = new Node<T>(null,null,t);
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
        T data;
        int state;

        Node(Node<T> left, Node<T> right, T data){
            this.left = left;
            this.right = right;
            this.data = data;
        }

    }

    public static void main(String[] args) {
        BinarySearchTree bTree = new BinarySearchTree();
        bTree.insert(2);
        bTree.insert(1);
        bTree.insert(3);
        bTree.postOrderWithoutRecurs();
        bTree.layerTraverse();
    }
}
