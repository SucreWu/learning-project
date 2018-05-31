package com.wujie.learning.collection;

/**
 * @description: 平衡二叉树
 * @author: wujie
 * @create: 2018-05-31 20:28
 **/
public class BalancedBinaryTree<T> {

    Node<T> root;

    class Node<T>{
        private T data;
        //子树深度
        private int depth;
        //左右子树高度差
        private int balance;
        private Node<T> left;
        private Node<T> right;

        public Node(T data){
            this.data = data;
            this.depth = 1;
            this.balance = 0;
            left = null;
            right = null;
        }
    }
}
