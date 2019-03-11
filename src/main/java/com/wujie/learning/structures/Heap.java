package com.wujie.learning.structures;

/**
 * @Description: 堆, 大顶堆
 * @Auther: wujie
 * @Date: 2019/3/11 17:16
 * @version: V1.0
 */
public class Heap {
    // 数组，从下标1开始存储数据
    private int[] a;

    // 数组剩余容量
    private int n;

    // 已存储个数
    private int count;

    public Heap(int capacity) {
        a = new int[capacity + 1];
        n = capacity;
        count = 0;
    }

    public void insert(int data) {
        if (count >= n) throw new IndexOutOfBoundsException("堆满了");
        ++count;
        a[count] = data;
        int i = count;
        // 从下往上堆化
        while (i / 2 > 0 && a[i] > a[i / 2]) {
            // 交换值
            int temp = a[i];
            a[i] = a[i / 2];
            a[i / 2] = temp;
            i = i / 2;
        }
    }

    public void removeMax() {
        if (count == 0) throw new IndexOutOfBoundsException("堆为空");
        a[1] = a[count];
        --count;
        heapify(a, count, 1);
    }

    /*
     * 自上往下堆化
     * @param a
     * @param n -- count
     * @param i -- 开始堆化的结点
     */
    private void heapify(int[] a, int n, int i) {
        while (true) {
            int index = i;
            // 考虑左右子结点两种情况
            if (i * 2 <= n && a[i * 2] > a[i]) index = i * 2;
            if (i * 2 + 1 <= n && a[i * 2 + 1] > a[i]) index = i * 2 + 1;
            if (index == i){
                break;
            }
            int temp = a[index];
            a[index] = a[i];
            a[i] = temp;
            i = index;
        }
    }


}
