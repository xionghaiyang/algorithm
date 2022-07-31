package com.sean.leetcode;

/**
 * 合并两个链表
 * https://leetcode-cn.com/problems/merge-in-between-linked-lists/
 */
public class LeetCode1669 {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode cur = list1;
        for (int i = 1; i < a; i++) {
            cur = cur.next;
        }
        ListNode leftNode = cur;
        for (int i = a; i <= b; i++) {
            cur = cur.next;
        }
        ListNode rightNode = cur.next;
        leftNode.next = list2;
        while (leftNode.next != null) {
            leftNode = leftNode.next;
        }
        leftNode.next = rightNode;
        return list1;
    }

}
