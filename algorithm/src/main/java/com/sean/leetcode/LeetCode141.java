package com.sean.leetcode;

import java.util.HashSet;

/**
 * 环形链表
 * https://leetcode-cn.com/problems/linked-list-cycle/
 */
public class LeetCode141 {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    private static ListNode getListNode(int[] arr, int pos) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead, end = null;
        int count = -1;
        for (int i : arr) {
            ListNode nextListNode = new ListNode(i);
            cur.next = nextListNode;
            count++;
            if (pos == count) {
                end = nextListNode;
            }
            cur = cur.next;
        }
        cur.next = end;
        return dummyHead.next;
    }

    public static boolean hasCycle(ListNode head) {
        HashSet<ListNode> hashSet = new HashSet<>();
        ListNode cur = head;
        while (cur != null) {
            if (!hashSet.add(cur)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(hasCycle(getListNode(new int[]{3,2,0,-4},1)));
        System.out.println(hasCycle(getListNode(new int[]{1,2},0)));
        System.out.println(hasCycle(getListNode(new int[]{1},-1)));
    }
}
