package com.wujie.learning.collection;

/**
 * @description: 红黑树
 * @author: wujie
 * @create: 2018-11-28 14:59
 **/
public class RBTree<T extends Comparable<T>> {

    private static final boolean RED   = false;
    private static final boolean BLACK = true;

    class RBNode<T>{
        T vlaue;
        RBNode<T> left;
        RBNode<T> right;
        RBNode<T> parent;
        Boolean color = BLACK;

        RBNode(){}
        RBNode(T vlaue){
            this.vlaue = vlaue;
        }
        RBNode(T vlaue, Boolean color){
            this.vlaue = vlaue;
            this.color = color;
        }
        RBNode(T vlaue, Boolean color, RBNode<T> parent){
            this.vlaue = vlaue;
            this.color = color;
            this.parent = parent;
        }
    }

    private RBNode<T> root;

    /**
     * 左旋，传入旋转子树的根结点
     * @param p
     */
    public void rotateLeft(RBNode<T> p){
        if (p != null){
            RBNode<T> r = p.right;
            p.right = r.left;
            if (r.left != null){
                r.left.parent = p;
            }
            // 父结点修改
            r.parent = p.parent;
            if (p.parent == null){
                root = r;
            }else if (p.parent.left == p){
                p.parent.left = r;
            }else {
                p.parent.right = r;
            }
            p.parent = r;
            r.left = p;
        }
    }

    /**
     * 右旋，传入旋转子树的根结点
     * @param p
     */
    public void rotateRight(RBNode<T> p){
        if (p != null){
            RBNode<T> l = p.left;
            p.left = l.right;
            if (l.right != null){
                l.right.parent = p;
            }
            // 父结点修改
            l.parent = p.parent;
            if (p.parent == null){
                root = l;
            }else if (p.parent.left == p){
                p.parent.left = l;
            }else {
                p.parent.right = l;
            }
            p.parent = l;
            l.right = p;
        }
    }

    /**
     * 插入
     * @param t
     */
    public void insert(T t){
        if (t == null){
            throw new NullPointerException("Data is null!");
        }
        if (root == null){
            root = new RBNode<T>(t);
        }
        RBNode<T> parent = root;
        // 查找插入结点位置
        while (parent != null){
            int compare = t.compareTo(parent.vlaue);
            if (compare < 0){
                parent = parent.left;
            }else if (compare > 0){
                parent = parent.right;
            }else {
                return;
            }
        }
        RBNode<T> current = new RBNode<>(t, RED, parent);
        int compare = t.compareTo(parent.vlaue);
        if (compare < 0){
            parent.left = current;
        }else {
            parent.right = current;
        }
        fixAfterInsert(current);
    }

    /**
     * 插入后调整
     * @param x
     */
    private void fixAfterInsert(RBNode<T> x) {
        while(x != null && x != root && x.parent.color == RED){
            // 先判断插入结点的父亲结点是祖父结点的左孩子还是右孩子，从而确定叔结点的位置
            if (x.parent == x.parent.parent.left){
                RBNode<T> u = x.parent.parent.right;
                // 叔结点为空的话，就直接进行旋转
                if (u != null && u.color == RED){
                    x.parent.color = BLACK;
                    u.color = BLACK;
                    x.parent.parent.color = RED;
                    x = x.parent.parent;
                }else {
                    // 当前结点是父结点的右孩子，需要先对父结点进行左旋
                    if (x.parent.right == x){
                        // 经过左旋，父结点会在最下面
                        x = x.parent;
                        rotateLeft(x);
                    }
                    x.parent.color = BLACK;
                    x.parent.parent.color = RED;
                    rotateRight(x.parent.parent);
                }
            }else {
                RBNode<T> u = x.parent.parent.left;
                if (u != null && u.color == RED){
                    x.parent.color = BLACK;
                    u.color = BLACK;
                    x.parent.parent.color = RED;
                    x = x.parent.parent;
                }else {
                    if (x.parent.left == x){
                        x = x.parent;
                        rotateRight(x);
                    }
                    x.parent.color = BLACK;
                    x.parent.parent.color = RED;
                    rotateLeft(x.parent.parent);
                }
            }
        }
        root.color = BLACK;
    }
}
