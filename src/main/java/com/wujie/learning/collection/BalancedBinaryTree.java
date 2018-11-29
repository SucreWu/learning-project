package com.wujie.learning.collection;

import java.util.TreeMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @description: 平衡二叉树，遍历方法就不写了，请参考BinarySearchTree
 * @author: wujie
 * @create: 2018-05-31 20:28
 **/
public class BalancedBinaryTree<T extends Comparable<T>>{

    // 根结点
    AVLNode<T> root;

    /**
     * 内部结点类
     * @param <T>
     */
    class AVLNode<T>{
        private T data;
        // 结点高度，指当前结点到叶子结点的最长路径
        private int height;
        // 结点深度，指从根结点到当前结点的最长路径
        private int depth;
        // 左右子树高度差
        private int balance;
        private AVLNode<T> left;
        private AVLNode<T> right;

        public AVLNode(T data){
            this.data = data;
            this.depth = 1;
            this.balance = 0;
            left = null;
            right = null;
        }
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty() {
        return root==null;
    }

    /**
     * 计算平衡二叉树的高度
     * @return
     */
    public int height() {
        return height(root);
    }

    /**
     * 计算当前结点的高度
     * @param x
     * @return
     */
    public int height(AVLNode<T> x){
        return x == null ? -1 : x.height;
    }

    /**
     * 查找最大结点，之前用的while，这里就用递归好了
     * @param current
     * @return
     */
    public AVLNode<T> findMaxNode(AVLNode<T> current){
        if (current == null) {
            return null;
        }else if (current.right == null){
            return current;
        }
        return findMaxNode(current.right);
    }

    /**
     * 查找最大值
     * @return
     */
    public T findMax(){
        if (root == null){
            return null;
        }else {
            return findMaxNode(root).data;
        }
    }

    /**
     * 查找最小结点
     * @param current
     * @return
     */
    public AVLNode<T> findMinNode(AVLNode<T> current){
        if (current == null){
            return null;
        }else if (current.left == null){
            return current;
        }
        return findMinNode(current.left);
    }

    /**
     * 查找最小值
     * @return
     */
    public T findMin(){
        if (root == null){
            return null;
        }else {
            return findMinNode(root).data;
        }
    }

    /**
     * 失去平衡的第一种情况：① 在结点X的左孩子结点的左子树中插入元素
     * 左左单旋转(右旋)，左子作父，父为右子，(左子)右孙变(父)左孙
     * @param x
     * @return
     */
    public AVLNode<T> singleRightRotate(AVLNode<T> x){
        AVLNode<T> lChild = x.left;
        // 下面两步顺序不能颠倒，先把左孩子的右子树变为x的左子树
        x.left = lChild.right;
        // 再将x变为左孩子的右子树
        lChild.right = x;
        // 重算高度
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        lChild.height = Math.max(height(lChild.left), x.height) + 1;
        // 返回新的根结点
        return lChild;
    }

    /**
     * 失去平衡的第二种情况：② 在结点X的右孩子结点的右子树中插入元素
     * 右右单旋转(左旋)，右子作父，父为左子，(右子)左孙变(父)右孙
     * @param x
     * @return
     */
    public AVLNode<T> singleLeftRotate(AVLNode<T> x){
        AVLNode<T> rChild = x.right;
        // 右孩子的左子树变为x的右子树
        x.right = rChild.left;
        // x变为右孩子的左子树
        rChild.left = x;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        rChild.height = Math.max(height(rChild.right), x.height) + 1;
        return rChild;
    }

    /**
     * 失去平衡的第三种情况：③在结点X的左孩子结点的右子树中插入元素
     * LR旋转(先左旋再右旋)
     * @param x
     * @return
     */
    public AVLNode<T> doubleLeftRotate(AVLNode<T> x){
        // 先将根结点的左孩子左旋
        x.left = singleLeftRotate(x.left);
        // 再将x右旋
        return singleRightRotate(x);
    }

    /**
     * 失去平衡的第四种情况：④在结点X的右孩子结点的左子树中插入元素
     * RL旋转(先右旋再左旋)
     * @param x
     * @return
     */
    public AVLNode<T> doubleRightRotate(AVLNode<T> x){
        // 先将根结点的右孩子右旋
        x.left = singleRightRotate(x.left);
        // 再将x左旋
        return singleLeftRotate(x);
    }

    /**
     * 插入
     * @param data
     */
    public void insert(T data){
        if (data==null){
            throw new RuntimeException("data can\'t be null!");
        }
        this.root = insert(root, data);
    }

    /**
     * 插入新结点，root会传根结点（这个root的命名是指相对每一颗子树的根结点）
     * @param root
     * @param data
     * @return
     */
    public AVLNode<T> insert(AVLNode<T> root, T data){
        // 没有孩子结点，创建新结点插入
        if (root == null){
            root = new AVLNode<T>(data);
        }else if (data.compareTo(root.left.data) < 0){
            root.left = insert(root.left, data);
            // 计算高度差
            if (Math.abs(height(root.left) - height(root.right)) == 2){
                // 判断新结点是插入点的左孩子还是右孩子
                if (data.compareTo(root.left.data) < 0){
                    // LL单旋转，需要做右旋操作
                    root = singleRightRotate(root);
                }else {
                    // LR旋转
                    root = doubleLeftRotate(root);
                }
            }
        }else if(data.compareTo(root.left.data) > 0){
            root.right = insert(root.right, data);
            if (Math.abs(height(root.left) - height(root.right)) == 2){
                if (data.compareTo(root.left.data) > 0){
                    // RR单旋转，需要做左旋操作
                    root = singleLeftRotate(root);
                }else {
                    // RL旋转
                    root = doubleRightRotate(root);
                }
            }
        }else{
            return null;
        }

        root.height = Math.max(height(root.left), height(root.right)) + 1;
        return root;
    }

    /**
     * 删除
     * @param t
     */
    public void remove(T t){
        if (t == null){
            throw new RuntimeException("data can\'t be null!");
        }
        this.root = remove(root, t);
    }

    /**
     * 删除结点，这里采用递归的方式
     * @param root
     * @param data
     * @return
     */
    public AVLNode<T> remove(AVLNode<T> root, T data){
        if (root == null){
            return null;
        }
        int compare = data.compareTo(root.data);
        if (compare < 0){
            root.left = remove(root.left, data);
            if (Math.abs(height(root.left) - height(root.right)) == 2){
                // LL，右旋
                if (data.compareTo(root.left.data) < 0){
                    root = singleRightRotate(root);
                }else {
                    // LR
                    root = doubleLeftRotate(root);
                }
            }
        }else if (compare > 0){
            root.right = remove(root.right, data);
            if (Math.abs(height(root.left) - height(root.right)) == 2){
                // RL，左旋
                if (data.compareTo(root.right.data) < 0){
                    root = doubleRightRotate(root);
                }else {
                    // RR
                    root = singleLeftRotate(root);
                }
            }
        // 以找到需要删除结点的位置，且左右孩子都不为空，删除方法同BinarySearchTree一样，找出后继结点替换
        }else if ((root.left != null) && (root.right != null)){
            // 将后继结点的data赋值给根结点
            root.data = findMinNode(root.right).data;
            // 根据后继结点的值已经传入当前根结点来删除后继结点
            root.right = remove(root.right, root.data);
        }else if (root.left != null){
            root = root.left;
        }else if (root.right != null){
            root = root.right;
        }else {
            root = null;
        }
        // 计算高度
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        return root;
    }

    public static void main(String[] args) {
    }
}
