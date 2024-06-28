package com.sean.leetcode;

/**
 * 删除链表的倒数第 N 个结点
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 */
public class LeetCode19 {

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

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode fast = head, slow = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        if (fast == null) {
            return head.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    private static ListNode createListNode(int[] arr) {
        ListNode head = new ListNode();
        ListNode cur = head;
        for (int i : arr) {
            ListNode nextNode = new ListNode(i);
            cur.next = nextNode;
            cur = cur.next;
        }
        return head.next;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ListNode listNode = removeNthFromEnd(createListNode(arr), 7);
        ListNode cur = listNode;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }

}
