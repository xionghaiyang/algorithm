package com.sean.leetcode;

/**
 * 链表的中间结点
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
 */
public class LeetCode876 {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode middleNode(ListNode head) {
        int size = 0;
        ListNode cur = head;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        int n = 0;
        cur = head;
        while (n < size / 2) {
            n++;
            cur = cur.next;
        }
        return cur;
    }
}
