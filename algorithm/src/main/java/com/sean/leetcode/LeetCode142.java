package com.sean.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 环形链表 II
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/
 */
public class LeetCode142 {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static ListNode detectCycle(ListNode head) {
        Set<ListNode> hashSet = new HashSet<>();
        ListNode currentNode = head;
        while (currentNode != null) {
            if (hashSet.contains(currentNode)) {
                return currentNode;
            } else {
                hashSet.add(currentNode);
            }
            currentNode = currentNode.next;
        }
        return null;
    }


}
