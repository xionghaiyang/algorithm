package com.sean.leetcode;

/**
 * 交换链表中的节点
 * https://leetcode-cn.com/problems/swapping-nodes-in-a-linked-list/
 */
public class LeetCode1721 {

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

    public static ListNode swapNodes(ListNode head, int k) {
        ListNode left = head, right = head;
        for (int i = 0; i < k - 1; i++) {
            right = right.next;
        }
        ListNode node1 = right;
        while (right.next != null) {
            left = left.next;
            right = right.next;
        }
        ListNode node2 = left;
        int temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;
        return head;
    }
}
