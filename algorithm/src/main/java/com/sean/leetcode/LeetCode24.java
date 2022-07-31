package com.sean.leetcode;

/**
 * 两两交换链表中的节点
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 */
public class LeetCode24 {

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

    public static ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode dummy = dummyHead;
        while (dummy.next != null && dummy.next.next != null) {
            ListNode node1 = dummy.next;
            ListNode node2 = node1.next;
            dummy.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            dummy = node1;
        }
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

    public static void main(String[] args) {
        ListNode listNode = swapPairs(createListNode(new int[]{2, 1, 4, 3}));
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
