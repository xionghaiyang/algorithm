package com.sean.leetcode;

/**
 * 分隔链表
 * https://leetcode-cn.com/problems/split-linked-list-in-parts/
 */
public class LeetCode725 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode[] splitListToParts(ListNode head, int k) {
        ListNode currentNode = head;
        int length = 0;
        while (currentNode != null) {
            length++;
            currentNode = currentNode.next;
        }
        int width = length / k, rem = length % k;
        ListNode[] res = new ListNode[k];
        currentNode = head;
        for (int i = 0; i < k; i++) {
            ListNode dummyHead = new ListNode(0), dummy = dummyHead;
            for (int j = 0; j < width + (i < rem ? 1 : 0); j++) {
                dummy.next = new ListNode(currentNode.val);
                dummy = dummy.next;
                if (currentNode != null) {
                    currentNode = currentNode.next;
                }
            }
            res[i] = dummyHead.next;
        }
        return res;
    }
}
