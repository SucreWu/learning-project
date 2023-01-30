package com.wujie.learning.util;

import java.util.List;

public class ListReverseUtil {

    class ListNode {
        int val;
        ListNode next = null;
        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode reverse(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        ListNode pre = null;
        ListNode current = head;
        ListNode next = null;
        ListNode result = null;
        while (current != null){
            next = current.next;
            if (next == null){
                result = current;
            }
            current.next = pre;
            pre = current;
            current = next;
        }
        return result;
    }


}
