package com.wujie.learning.collection;

/**
 * @description: 平衡二叉树
 * @author: wujie
 * @create: 2018-05-31 20:28
 **/
public class BalancedBinaryTree<T extends Comparable<T>>{

    AVLNode<T> root;

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

    public boolean isEmpty() {
        return root==null;
    }

    // 失去平衡的第一种情况：① 在结点X的左孩子结点的左子树中插入元素
    // 左左单旋转(右旋)，左子作父，父为右子，(左子)右孙变(父)左孙
    public AVLNode<T> singleRightRotate(AVLNode<T> x){
        AVLNode<T> lChild = x.left;
        // 下面两步顺序不能颠倒，先把左孩子的右子树变为x的左子树
        x.left = lChild.right;
        // 再将x变为左孩子的右子树
        lChild.right = x;
        // 重算高度
        x.height = Math.max(x.left.height, x.right.height) + 1;
        lChild.height = Math.max(lChild.left.height, x.height) + 1;
        // 返回新的根结点
        return lChild;
    }

    // 失去平衡的第二种情况：② 在结点X的右孩子结点的右子树中插入元素
    // 右右单旋转(左旋)，右子作父，父为左子，(右子)左孙变(父)右孙
    public AVLNode<T> singleLeftRotate(AVLNode<T> x){
        AVLNode<T> rChild = x.right;
        // 右孩子的左子树变为x的右子树
        x.right = rChild.left;
        // x变为右孩子的左子树
        rChild.left = x;
        x.height = Math.max(x.left.height, x.right.height) + 1;
        rChild.height = Math.max(rChild.right.height, x.height) + 1;
        return rChild;
    }

    // 失去平衡的第三种情况：③在结点X的左孩子结点的右子树中插入元素
    // LR旋转(先左旋再右旋)
    public AVLNode<T> doubleLeftRotate(AVLNode<T> x){
        // 先将根结点的左孩子左旋
        x.left = singleLeftRotate(x.left);
        // 再将x右旋
        return singleRightRotate(x);
    }

    // 失去平衡的第四种情况：④在结点X的右孩子结点的左子树中插入元素
    // RL旋转(先右旋再左旋)
    public AVLNode<T> doubleRightRotate(AVLNode<T> x){
        // 先将根结点的右孩子右旋
        x.left = singleRightRotate(x.left);
        // 再将x左旋
        return singleLeftRotate(x);
    }

    public void insert(T data){
        if (data==null){
            throw new RuntimeException("data can\'t be null ");
        }
        this.root = insert(root, data);
    }

    // 插入
    public AVLNode<T> insert(AVLNode<T> root, T data){
        // 没有孩子结点，创建新结点插入
        if (root == null){
            root = new AVLNode<T>(data);
        }else if (data.compareTo(root.left.data) < 0){
            root.left = insert(root.left, data);
            // 计算高度差
            if (Math.abs(root.left.height - root.right.height) == 2){
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
            if (Math.abs(root.left.height - root.right.height) == 2){
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

        root.height = Math.max(root.left.height, root.right.height) + 1;
        return root;
    }

}
