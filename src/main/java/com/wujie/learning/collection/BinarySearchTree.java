package com.wujie.learning.collection;

import java.util.*;

/**
 * @description: 二叉树
 * @author: wujie
 * @create: 2018-05-24 15:09
 **/
public class BinarySearchTree<T extends Comparable<T>> {

    // 内部结点类
    class Node<T>{
        public Node<T> left;
        public Node<T> right;
        public Node<T> parent;
        public T data;
        // 标记当前结点已经访问到哪一步。每次从子结点回溯回来的时候，都可以直接从当前结点里取出（用于非递归遍历的第二种实现）
        public int state;

        public Node(T data){
            this.data = data;
        }
    }

    // 根结点
    Node<T> root ;

    // 查找二叉树的最小结点
    private Node<T> minimum(Node<T> x){
        if (x == null){
            return null;
        }
        while (x.left != null){
            x = x.left;
        }
        return x;
    }

    // 查找二叉树的最小值
    private T getMinimum(){
        Node<T> x = minimum(root);
        if (x != null){
            return x.data;
        }
        return null;
    }

    // 查找二叉树的最大结点
    private Node<T> maximum(Node<T> x){
        if (x == null){
            return null;
        }
        while (x.right != null){
            x = x.right;
        }
        return x;
    }

    // 查找二叉树的最大值
    private T getMaximum(){
        Node<T> x = maximum(root);
        if (x != null){
            return x.data;
        }
        return null;
    }

    // 获取后继结点，即二叉树中数据值大于该结点的最小结点
    // 首先明确一点，既然是数值大于该结点，那么不可能在当前节点的左子树中寻找，所以不用考虑该结点左孩子的情况
    private Node<T> getSuccessor(Node<T> x){
        // 如果存在右孩子，则后继结点是当前结点右子树的最小结点
        if (x.right != null){
            return minimum(x);
        }
        // 如果没有右孩子，有以下两种情况：
        // ①该结点是一个左孩子，那么x的后继结点就是他的父结点
        // ②该结点是一个右孩子，查找x的最低父结点，且父结点必须具有左孩子，那么这个父结点就是x得后继结点
        Node<T> parent = x.parent;
//        while ((parent != null) && (parent.right == x))
        while ((parent != null) && (parent.left == null)){
            x = parent;
            parent = parent.parent;
        }
        return parent;
    }

    // 是否存在
    private boolean contains(T t){
        if (root == null || t == null){
            return false;
        }
        Node<T> current = root;
        int compare = t.compareTo(current.data);
        while (current != null){
            if (compare < 0) {
                current = current.left;
            }else if (compare > 0){
                current = current.right;
            }else {
                return true;
            }
        }
        return false;
    }

    // 根据特定的值查找结点
    private Node<T> find(T t){
        if (root == null || t == null){
            return null;
        }
        Node<T> current = root;
        int compare = t.compareTo(current.data);
        while (current != null){
            if (compare < 0) {
                current = current.left;
            }else if (compare > 0){
                current = current.right;
            }else {
                return current;
            }
        }
        return null;
    }

    // 插入
    private boolean insert(T t){
        // 新结点
        Node<T> newNode = new Node<>(t);
        // 新结点的父结点
        Node<T> parent = null;
        // 二叉树为空
        if (root == null){
            root = newNode;
            return true;
        }
        // 获取插入结点的位置
        Node<T> current = root;
        int compare = t.compareTo(current.data);
        while (current != null){
            parent = current;
            if (compare < 0){
                current = current.left;
            }else if (compare > 0) {
                current = current.right;
            }else {
                return false;
            }
        }
        current = newNode;
        current.parent = parent;
        // 判断新结点是父结点的左/右孩子
        if (current.data.compareTo(parent.data) < 0){
            parent.left = current;
        }else {
            parent.right = current;
        }
        return true;
    }

    // 删除，分三种情况，第一：左右孩子都为空的结点就直接删了，第二：左右都不为空，寻找二叉树中数据值大于该结点的最小结点（后继结点）
    // 第三：只有一个孩子结点，直接用孩子结点替换
    private boolean remove(Node<T> x){
        // 替代结点，用于第二种情况
        Node<T> alternative;
        // 左或者右孩子结点
        Node<T> child;
        Node<T> parent = x.parent;
        // 第二种情况
        if (x.left != null && x.right != null){
            alternative = getSuccessor(x);
            x.data = alternative.data;
            // 把当前结点设置为后继结点，便于下面的删除
            x = alternative;
        }
        // 因为如果是第二种情况，经过处理，后继结点只能是有一个孩子结点或者没有孩子结点
        // 获取子结点
        if (x.left != null){
            child = x.left;
        }else {
            child = x.right;
        }
        // 第三种情况，只有一个子结点
        if (child != null){
            // 将子结点关联父结点
            child.parent = x.parent;
        }
        // 删除的结点是根结点
        // 第二种没有子结点的情况，也会在这里被删除（将父结点的左/右孩子置为null）
        if (x.parent == null){
            root = child;
        }else if (x == x.parent.left){
            x.parent.left = child;
        }else {
            x.parent.right = child;
        }
//        比较通俗易懂的版本
//        if (x.left == null && x.right == null) {
//            // 根结点
//            if (parent == null) {
//                root = null;
//            }
//            if (parent.left == x) {
//                parent.left = null;
//            } else {
//                parent.right = null;
//            }
//        }else if (x.left != null && x.right != null){
//            alternative = getSuccessor(x);
//            x.data = alternative.data;
//            remove(alternative); // 递归删除
//        }else{
//            if (x.left != null){
//                child = x.left;
//            }else {
//                child = x.right;
//            }
//            child.parent = parent;
//            // 根结点
//            if (parent == null){
//                root = child;
//                child.parent = null;
//            }
//            // 分删除结点是父结点的左右子结点两种情况
//            if (parent.left == x){
//                parent.left = child;
//            }else {
//                parent.right = child;
//            }
//        }
        return true;
    }

    // 递归实现
    // 前序遍历（根 左 右）
    private void preOrder(Node<T> x){
        if (x.left != null){
            System.out.println(x.data);
            preOrder(x.left);
            preOrder(x.right);
        }
    }

    // 中序遍历（左 根 右）
    private void inOrder(Node<T> x){
        if (x.left != null){
            inOrder(x.left);
            System.out.println(x.data);
            inOrder(x.right);
        }
    }

    // 后序遍历（左 右 根）
    private void postOrder(Node<T> x) {
        if (x.left != null) {
            postOrder(x.left);
            postOrder(x.right);
            System.out.println(x.data);
        }
    }

    // 非递归前序遍历
    public void preOrderWithoutRecurs1() {
        Stack<Node<T>> stack = new Stack<>();
        Node<T> current = root;
        while ((!stack.isEmpty()) || (current != null)){
            if (current != null){
                System.out.println(current.data);
                stack.push(current);
                current = current.left;
            }else {
                current = stack.pop();
                current = current.right;
            }
        }
//        stack.push(root);
//        while (!stack.isEmpty()) {
//            current = stack.pop();
//            if (current != null) {
//                System.out.println(current.data);
//                stack.push(current.left);
//                stack.push(current.right);
//            }
//        }
    }

    // 非递归中序遍历
    public void inOrderWithoutRecurs1() {
        Stack<Node<T>> stack = new Stack<>();
        Node<T> current = root;
        while ((!stack.isEmpty()) || (current != null)){
            while (current != null){
                stack.push(current);
                current = current.left;
            }
            // 如果栈不为空，说明上一步的左孩子已经全部入栈
            if (!stack.isEmpty()){
                current = stack.pop();
                System.out.println(current.data);
                // 因为入栈的结点的左孩子已经必入栈了，所以直接访问右孩子即可
                current = current.right;
            }
//            if (current != null){
//                stack.push(current);
//                current = current.left;
//            }else {
//                current = stack.pop();
//                System.out.println(current.data);
//                current = current.right;
//            }
        }
    }

    // 非递归后序遍历
    public void postOrderWithoutRecurs1() {
        Stack<Node<T>> stack = new Stack<>();
        Node<T> current = root;
        // 用于记录已被访问过的右孩子结点
        Node<T> prev = root;
        while ((!stack.isEmpty()) || (current != null)) {
            // 同中序一样都需要先把左孩子入栈
            while (current != null){
                stack.push(current);
                current = current.left;
            }
            // 第二步去访问当前结点父结点(栈顶元素)的右孩子
            if (!stack.isEmpty()){
                // 获取栈顶元素的右孩子
                Node<T> rightTemp = stack.peek().right;
                // 判断右孩子是否为空或者被访问过
                if (rightTemp == null || rightTemp == prev){
                    current = stack.pop();
                    System.out.println(current.data);
                    prev = current;
                    // 置为空，因为出栈的元素的左孩子已经被访问过，所以不用再判断
                    current = null;
                }else {
                    // 存在右孩子，则继续遍历右孩子
                    current = rightTemp;
                }
            }
        }
    }

    // 模拟递归函数的函数栈，state抽象于当前执行到哪一步，非递归遍历的第二种实现
    // 非递归前序遍历
    public void preOrderWithoutRecurs2() {
        if (root == null) {
            return;
        }

        MyStack<Node<T>> stack = new MyStack<>(50);
        Node<T> current;
        stack.push(root);
        while (!stack.isEmpty()){
            current = stack.getTop();
            if (current.state == 0){
                System.out.println(current.data);
                current.state = 1;
            }else if (current.state == 1){
                if (current.left != null){
                    stack.push(current.left);
                }
                current.state = 2;
            }else if (current.state == 2){
                if (current.right != null){
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
    public void inOrderWithoutRecurs2() {
        if (root == null){
            return;
        }
        MyStack<Node<T>> stack = new MyStack<>(50);
        Node<T> current;
        stack.push(root);
        while(!stack.isEmpty()){
            current = stack.getTop();
            if (current.state == 0){
                if (current.left != null){
                    stack.push(current.left);
                }
                current.state = 1;
            }else if (current.state == 1){
                System.out.println(current.data);
                current.state = 2;
            }else if (current.state == 2){
                if (current.right != null){
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
    public void postOrderWithoutRecurs2(){
        if (root == null){
            return;
        }
        MyStack<Node<T>> stack = new MyStack<>(50);
        Node<T> current;
        stack.push(root);
        while(!stack.isEmpty()){
            current = stack.getTop();
            if (current.state == 0){
                if (current.left != null){
                    stack.push(current.left);
                }
                current.state = 1;
            }else if (current.state == 1){
                if (current.right != null){
                    stack.push(current.right);
                }
                current.state = 2;
            }else if (current.state == 2){
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
            if (current.left != null){
                queue.offer(current.left);
            }
            if (current.right != null){
                queue.offer(current.right);
            }
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> btree = new BinarySearchTree<>();
        btree.insert(5);
        btree.insert(2);
        btree.insert(7);
        btree.insert(1);
        btree.insert(9);
        btree.insert(11);
        btree.inOrderWithoutRecurs1();
    }
}