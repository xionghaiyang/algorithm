package com.sean.leetcode;

import java.util.Stack;

/**
 * 链表中的下一个更大节点
 * https://leetcode-cn.com/problems/next-greater-node-in-linked-list/
 */
public class LeetCode1019 {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static int[] nextLargerNodes(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        int[] arr = new int[length];
        node = head;
        for (int i = 0; i < length && node != null; i++, node = node.next) {
            arr[i] = node.val;
        }
        int[] res = new int[length];
        for (int i = 0; i < length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                res[stack.peek()] = arr[i];
                stack.pop();
            }
            stack.push(i);
        }
        return res;
    }


}
