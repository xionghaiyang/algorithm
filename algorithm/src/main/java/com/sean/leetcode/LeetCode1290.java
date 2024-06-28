package com.sean.leetcode;

/**
 * 二进制链表转整数
 * https://leetcode-cn.com/problems/convert-binary-number-in-a-linked-list-to-integer/
 */
public class LeetCode1290 {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static int getDecimalValue(ListNode head) {
        int res = 0;
        ListNode cur = head;
        while (cur != null) {
            res = res * 2 + cur.val;
            cur = cur.next;
        }
        return res;
    }

}
