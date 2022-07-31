package com.sean.nowcoder;

public class NowCoderBM9 {

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        ListNode cur = head;
        ListNode fast = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            pre = cur;
            cur = cur.next;
        }
        pre.next = cur.next;
        return dummyHead.next;
    }

}
