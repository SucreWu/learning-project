package com.wujie.learning.leetcode;

/**
 * 两数相加
 *
 * 给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class leetcode2 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int add = 0;
        ListNode first = new ListNode(0);
        ListNode tail = first;
        while (l1 != null || l2 != null){
            int v1 = l1 != null ? l1.val : 0;
            int v2 = l2 != null ? l2.val : 0;
            tail.next = new ListNode((v1 + v2 + add) % 10);
            tail = tail.next;
            //进位的数最大也就是1
            add = (v1 + v2)/10;
            if (l1 != null){ l1 = l1.next;}
            if (l2 != null){ l2 = l2.next;}
        }
        return first.next;
    }

    //暴力方法
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        int result = calculate(l1) + calculate(l2);
        String str = result+"";
        ListNode first = new ListNode(Integer.parseInt(str.substring(str.length()-1)));
        ListNode tail = first;
        for (int i = str.length()-1; i >= 1; i--){
            tail.next = new ListNode(Integer.parseInt(str.substring(i-1,i)));
            tail = tail.next;
        }
        return first;
    }

    public static int calculate(ListNode node){
        int index = 0;
        int result = 0;
        while (node != null){
            result += node.val * Math.pow(10,index);
            index++;
            node = node.next;
        }
        return result;
    }

    public static void main(String[] args) {
        ListNode first = new ListNode(0);
        ListNode tail = first;
        for (int i = 1; i < 3; i++){
            tail.next = new ListNode(i);
            tail = tail.next;
        }

        ListNode first2 = new ListNode(4);
        ListNode tail2 = first2;
        for (int i = 5; i < 8; i++){
            tail2.next = new ListNode(i);
            tail2 = tail2.next;
        }

        ListNode result = addTwoNumbers(first, first2);
        tail = result;
        while (tail != null){
            System.out.print(tail.val);
            tail = tail.next;
        }

    }
}

