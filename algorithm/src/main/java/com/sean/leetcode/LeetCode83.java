package com.sean.leetcode;

/**
 * 删除排序链表中的重复元素
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 */
public class LeetCode83 {

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

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
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
        printListNode(deleteDuplicates(createListNode(new int[]{1, 1, 2})));
        System.out.println("============================================");
        printListNode(deleteDuplicates(createListNode(new int[]{1, 1, 2, 3, 3})));
    }
}
