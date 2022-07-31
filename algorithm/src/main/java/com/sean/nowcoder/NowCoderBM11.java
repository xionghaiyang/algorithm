package com.sean.nowcoder;

public class NowCoderBM11 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode reverseList(ListNode pHead) {
        if (pHead == null) {
            return null;
        }
        ListNode cur = pHead;
        ListNode pre = null;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    public static ListNode addInList(ListNode head1, ListNode head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        head1 = reverseList(head1);
        head2 = reverseList(head2);
        int carry = 0;
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;
        while (head1 != null || head2 != null || carry != 0) {
            int val1 = head1 != null ? head1.val : 0;
            int val2 = head2 != null ? head2.val : 0;
            int temp = val1 + val2 + carry;
            cur.next = new ListNode(temp % 10);
            cur = cur.next;
            carry = temp / 10;
            if (head1 != null) {
                head1 = head1.next;
            }
            if (head2 != null) {
                head2 = head2.next;
            }
        }
        return reverseList(dummyHead.next);
    }

}
