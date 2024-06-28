package com.sean.leetcode;

/**
 * 对链表进行插入排序
 * https://leetcode-cn.com/problems/insertion-sort-list/
 */
public class LeetCode147 {

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

    public static ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode lastSortedNode = head, curNode = head.next;
        while (curNode != null) {
            if (lastSortedNode.val <= curNode.val) {
                lastSortedNode = lastSortedNode.next;
            } else {
                ListNode preNode = dummyHead;
                while (preNode.next.val <= curNode.val) {
                    preNode = preNode.next;
                }
                lastSortedNode.next = curNode.next;
                curNode.next = preNode.next;
                preNode.next = curNode;
            }
            curNode = lastSortedNode.next;
        }
        return dummyHead.next;
    }


}
