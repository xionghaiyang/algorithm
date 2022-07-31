package com.sean.lintcode;

public class LintCode174 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (n <= 0) {
            return null;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        for (int i = 0; i < n; i++) {
            head = head.next;
        }
        while (head != null) {
            pre = pre.next;
            head = head.next;
        }
        pre.next = pre.next.next;
        return dummyHead.next;
    }

}
