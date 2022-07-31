package com.sean.leetcode;

import java.util.HashSet;

/**
 * 相交链表
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 */
public class LeetCode160 {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> hashSet = new HashSet<>();
        ListNode cur = headA;
        while (cur != null) {
            hashSet.add(cur);
            cur = cur.next;
        }
        cur = headB;
        while (cur != null) {
            if (hashSet.contains(cur)) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }
}
