package com.sean.leetcode;

/**
 * 移除链表元素
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 */
public class LeetCode203 {

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

    public static ListNode removeElements(ListNode head, int val) {
        ListNode dunmyHead = new ListNode(0, head);
        ListNode preNode = dunmyHead, currentNode = head;
        while (currentNode != null) {
            if (currentNode.val == val) {
                preNode.next = currentNode.next;
            } else {
                preNode = currentNode;
            }
            currentNode = currentNode.next;
        }
        return dunmyHead.next;
    }

}
