package com.sean.nowcoder;

public class NowCoderBM12 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode merge(ListNode pHead1, ListNode phead2) {
        if (pHead1 == null) {
            return phead2;
        }
        if (phead2 == null) {
            return pHead1;
        }
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;
        while (pHead1 != null && phead2 != null) {
            if (pHead1.val < phead2.val) {
                cur.next = pHead1;
                pHead1 = pHead1.next;
            } else {
                cur.next = phead2;
                phead2 = phead2.next;
            }
            cur = cur.next;
        }
        if (pHead1 != null) {
            cur.next = pHead1;
        }
        if (phead2 != null) {
            cur.next = phead2;
        }
        return dummyHead.next;
    }

    public static ListNode sortInList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode left = head;
        ListNode mid = head.next;
        ListNode right = head.next.next;
        while (right != null && right.next != null) {
            left = left.next;
            mid = mid.next;
            right = right.next.next;
        }
        left.next = null;
        return merge(sortInList(head), sortInList(mid));
    }

}
