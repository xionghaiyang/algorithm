package com.sean.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 链表组件
 * https://leetcode-cn.com/problems/linked-list-components/
 */
public class LeetCode817 {

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

    public static int numComponents(ListNode head, int[] nums) {
        Set<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            hashSet.add(num);
        }
        int res = 0;
        ListNode currentNode = head;
        while (currentNode != null) {
            if (hashSet.contains(currentNode.val) && (currentNode.next == null || !hashSet.contains(currentNode.next.val))) {
                res++;
            }
            currentNode = currentNode.next;
        }
        return res;
    }

}
