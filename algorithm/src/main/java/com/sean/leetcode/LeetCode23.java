package com.sean.leetcode;

/**
 * 合并K个升序链表
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 */
public class LeetCode23 {

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

    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode res = null;
        for (ListNode listNode : lists) {
            res = mergeTwoLists(res, listNode);
        }
        return res;
    }

    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 != null ? l1 : l2;
        }
        ListNode dummyHead = new ListNode();
        ListNode cur = dummyHead, listNode1 = l1, listNode2 = l2;
        while (listNode1 != null && listNode2 != null) {
            if (listNode1.val < listNode2.val) {
                cur.next = listNode1;
                listNode1 = listNode1.next;
            } else {
                cur.next = listNode2;
                listNode2 = listNode2.next;
            }
            cur = cur.next;
        }
        cur.next = listNode1 != null ? listNode1 : listNode2;
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
        ListNode[] listNodes = {createListNode(new int[]{1, 4, 5}), createListNode(new int[]{1, 3, 4}), createListNode(new int[]{2, 6})};
        ListNode listNode = mergeKLists(listNodes);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

}
