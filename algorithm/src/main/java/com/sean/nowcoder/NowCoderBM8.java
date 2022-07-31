package com.sean.nowcoder;

public class NowCoderBM8 {

    public static class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode FindKthToTail(ListNode pHead, int k) {
        ListNode fast = pHead;
        ListNode slow = pHead;
        for (int i = 0; i < k; i++) {
            if (fast != null) {
                fast = fast.next;
            } else {
                return null;
            }
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
