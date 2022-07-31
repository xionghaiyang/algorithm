package com.sean.leetcode;

import java.util.Stack;

/**
 * 分隔链表
 * https://leetcode-cn.com/problems/partition-list/
 */
public class LeetCode86 {

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

    public static ListNode partition(ListNode head, int x) {
        Stack<ListNode> smallStack = new Stack<>();
        Stack<ListNode> bigStack = new Stack<>();
        ListNode currentNode = head;
        while (currentNode != null) {
            if (currentNode.val < x) {
                smallStack.push(currentNode);
            } else {
                bigStack.push(currentNode);
            }
            currentNode = currentNode.next;
        }
        ListNode res = null;
        while (!bigStack.isEmpty()) {
            ListNode listNode = bigStack.pop();
            listNode.next = res;
            res = listNode;
        }
        while (!smallStack.isEmpty()) {
            ListNode listNode = smallStack.pop();
            listNode.next = res;
            res = listNode;
        }
        return res;
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
        printListNode(partition(createListNode(new int[]{1,4,3,2,5,2}),3));
        System.out.println("==============================================");
        printListNode(partition(createListNode(new int[]{2,1}),2));
    }
}
