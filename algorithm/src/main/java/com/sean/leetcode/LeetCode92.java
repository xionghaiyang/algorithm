package com.sean.leetcode;

import java.util.Stack;

/**
 * 反转链表 II
 * https://leetcode-cn.com/problems/reverse-linked-list-ii/
 */
public class LeetCode92 {

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

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode dummy = dummyHead;
        for (int i = 1; i < left; i++) {
            dummy = dummy.next;
        }
        ListNode leftNode = dummy;
        Stack<ListNode> stack = new Stack<>();
        for (int i = left; i <= right; i++) {
            dummy = dummy.next;
            stack.push(dummy);
        }
        ListNode rightNode = dummy.next;
        while (!stack.isEmpty()) {
            ListNode listNode = stack.pop();
            leftNode.next = listNode;
            leftNode = listNode;
        }
        leftNode.next = rightNode;
        return dummyHead.next;
    }

    private static ListNode createListNode(int[] arr) {
        ListNode dummyHead = new ListNode();
        ListNode cur = dummyHead;
        for (int i : arr) {
            ListNode nextNode = new ListNode(i);
            cur.next = nextNode;
            cur = cur.next;
        }
        return dummyHead.next;
    }

    private static void printListNode(ListNode listNode) {
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    public static void main(String[] args) {
        printListNode(reverseBetween(createListNode(new int[]{1,2,3,4,5}),2,4));
        System.out.println("=============================================================");
        printListNode(reverseBetween(createListNode(new int[]{5}),1,1));
    }
}
