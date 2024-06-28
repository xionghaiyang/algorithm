package com.sean.leetcode;

/**
 * 合并两个有序链表
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 */
public class LeetCode21 {

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

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null && l2 != null) {
            return l2;
        }
        if (l1 != null && l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
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
        ListNode node01 = createListNode(new int[]{1, 3, 5});
        ListNode node02 = createListNode(new int[]{2, 4, 6});
        ListNode listNode = mergeTwoLists(node01, node02);
        ListNode cur = listNode;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }


}
