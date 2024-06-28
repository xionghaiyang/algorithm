package com.sean.leetcode;

/**
 * 旋转链表
 * https://leetcode-cn.com/problems/rotate-list/
 */
public class LeetCode61 {

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

    public static ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        int size = 1;
        ListNode currentNode = head;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
            size++;
        }
        int n = size - k % size;
        if (n == size) {
            return head;
        }
        //闭环
        currentNode.next = head;
        while (n > 0) {
            currentNode = currentNode.next;
            n--;
        }
        ListNode res = currentNode.next;
        //断开环
        currentNode.next = null;
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

    public static void main(String[] args) {
        ListNode listNode = rotateRight(createListNode(new int[]{1, 2, 3, 4, 5}), 2);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

}
