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
     * 根据值获取结点
     * @param t
     * @return
     */
    public RBNode<T> getNode(T t){
        if (t == null) throw new NullPointerException("Value is null!");
        RBNode<T> x = root;
        while (x != null){
            int compare = t.compareTo(x.vlaue);
            if (compare < 0){
                x = x.left;
            }else if (compare > 0){
                x = x.right;
            }else{
                return x;
            }
        }
        return null;
    }

    /**
     * 中序后继
     * @param x
     * @return
     */
    public RBNode<T> successor(RBNode<T> x){
        if (x == null){
            return null;
        }else if (x.right != null){
            RBNode<T> r = x.right;
            while (r.left != null){
                r = r.left;
            }
            return r;
        }else {
            RBNode<T> p = x.parent;
            while (p != null && p.right == x){
                x = p;
                p = p.parent;
            }
            return p;
        }
    }


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
                // 叔结点也为红色，就将父结点，叔结点和祖父节点都变色，再从祖父节点开始检查
                if (u != null && u.color == RED){
                    x.parent.color = BLACK;
                    x.parent.parent.color = RED;
                    u.color = BLACK;
                    x = x.parent.parent;
                // 叔结点为空的话，就直接进行旋转
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

    /**
     * 删除
     * @param t
     */
    public void delete(T t){
        if (t == null) throw new NullPointerException("Value is null!");
        RBNode<T> x = getNode(t);
        if (x != null && x.left != null && x.right != null){
            RBNode<T> s = successor(x);
            // 先赋值，再指向s
            x.vlaue = s.vlaue;
            x = s;
        }
        RBNode<T> replacement = (x.left != null ? x.left : x.right);
        if (replacement != null){
            replacement.parent = x.parent;
            if (x.parent == null){
                root = replacement;
            }else if (x.parent.left == x){
                x.parent.left = replacement;
            }else {
                x.parent.right = replacement;
            }
            x.left = x.right = x.parent = null; // help GC
            if (x.color == BLACK){
                fixAfterDelete(replacement);
            }
        }else {
            if (x.color == BLACK){
                fixAfterDelete(x);
            }

            if (x.parent != null){
                if (x.parent.left == x){
                    x.parent.left = null;
                }else {
                    x.parent.right = null;
                }
            }
            x.parent = null;// help GC
        }
    }

    private void fixAfterDelete(RBNode<T> x) {
        if (x != root && x.color == BLACK){
            
        }
    }

}
